 public void saveText() throws SQLException {

        if (!getFileUploadData().getTextInput().equals("")) { //open db connection 
            {
                getDbConnection().setDbUserName(String.valueOf(getPortalData().getLiferayFacesContext().getUser().getUserId()));
            }
            getDbConnection().financialDbConnection = getDbConnection().connectToFinancialDb();

            if (getDbConnection().financialDbConnection != null) {

                try {
                    getDbConnection().callableStatement = getDbConnection().financialDbConnection.prepareCall("{ ? = call save_input_text("
                            + "?)}");
                    getDbConnection().callableStatement.registerOutParameter(1, Types.BOOLEAN);
                    getDbConnection().callableStatement.setString(2, getFileUploadData().getTextInput());
                    getDbConnection().callableStatement.execute();
                    if (getDbConnection().callableStatement.getBoolean(1)) {
                        getMessages().messages("Complete");

                    } else {
                        getMessages().messages("Failed");

                    }

                } catch (SQLException e) {
                    e.getMessage();
                    getMessages().messages("Error on database function");
                } finally {
                    System.out.println("Successfully Added Data");

                    getDbConnection().callableStatement.close();
                    getDbConnection().financialDbConnection.close();

                }

            }
        }
    }
