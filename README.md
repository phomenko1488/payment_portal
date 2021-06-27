# payment_portal
test task for "A-Bank" company.
# Client create
## URL
localhost:1489/api/v1/clients/create
## Request example
{
   "first_name":"Имя",
   "last_name":"Фамилия",
   "accounts":[
      {
         "account_num":"123456789",
         "account_type":"card/simple",
         "balance":5000.00
      },
      {
         "account_num":"987654321",
         "account_type":"card/simple",
         "balance":10000.00
      }
   ]
}
##Response example
{
  "client_id": 123
}

# Find client accounts by client id
## URL
localhost:1489/api/v1/clients/{id}/accounts
## Request example
localhost:1489/api/v1/clients/222/accounts
##Response example
[
   {
      "account_id":654,
      "account_num":"123456789",
      "account_type":"card/simple",
      "balance":5000.0
   },
   {
      "account_id":655,
      "account_num":"987654321",
      "account_type":"card/simple",
      "balance":10000.0
   }
]
# Payment create
## URL
localhost:1489/api/v1/payments/create
## Request example
{
   "source_acc_id":654,
   "dest_acc_id":655,
   "amount":100.00,
   "reason":"назначение платежа"
}
##Response example
{
  "payment_id": 789
}

# Payments create
## URL
localhost:1489/api/v1/payments/createList
##Request example
[
   {
      "source_acc_id":654,
      "dest_acc_id":655,
      "amount":100.00,
      "reason":"назначение платежа"
   },
   {
      "source_acc_id":655,
      "dest_acc_id":654,
      "amount":1000.00,
      "reason":"назначение платежа"
   }
]
##Response example
[
   {
      "payment_id":789,
      "status":"ok"
   },
   {
      "payment_id":788,
      "status":"error"
   }
] 
# Payment list for a different set of parameters
## URL
localhost:1489/api/v1/payments/getList
## Request example
{
   "payer_id":33333,
   "recipient_id":13,
   "source_acc_id":2,
   "dest_acc_id":121213
}
##Response example
[
    {
        "payment_id": 1,
        "timestamp": "2021-06-27T18:40:22.716+00:00",
        "src_acc_num": 123456789,
        "dest_acc_num": 987654321,
        "amount": 100.00,
        "payer": {
            "first_name": "Имя",
            "last_name": "Фамилия"
        },
        "recipient": {
            "first_name": "Имя",
            "last_name": "Фамилия"
        }
    }
] 
