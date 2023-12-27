CREATE OR REPLACE FUNCTION  (
    input_text_java text
)
RETURNS text AS
$BODY$
BEGIN
    -- Check if input_text_java matches a regular expression
    IF input_text_java ~* '[A-Za-z~!@#$%^&*()_,.*]' THEN
        -- If it matches, insert into the sample_input_text table
        INSERT INTO sample_input_text (input_text) VALUES (input_text_java);
        -- Return true if inserted successfully
        RETURN TRUE;
    ELSE
        -- Return false if no match
        RETURN 'no match';
    END IF;
END;
$BODY$
LANGUAGE plpgsql;
