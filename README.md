# BankAPI

API :
 Transaction :
   
    POST http://localhost:8080/newTransaction
Content-Type: application/json

{
  "account_from": 0,
  "account_to": 5,
  "sum": 100000,
  "currency_shortname": "RUB",
  "expense_category": "PRD",
  "date": "2030-03-21"
}

  Client :
    
    POST http://localhost:8080/api/client/newLimit
Content-Type: application/json

{
  "account_id" : 0,
  "limitForServices": 5555.0
}

    POST http://localhost:8080/api/client/newLimit
Content-Type: application/json

{
  "account_id" : 11,
  "limitForProduct": 5555.0
}

    GET http://localhost:8080/api/client/get/15
    
return : Array of Limit_Exceed transaction's.





  
