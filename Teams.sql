UPDATE Matches 
SET scoreAway = -1
WHERE scoreAway is NULL
SELECT * FROM Matches