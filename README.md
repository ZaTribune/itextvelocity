# ITEXT Velocity
  
A Demo project for generating PDF reports in Spring Boot using Apache Velocity &amp; IText.  

<p align="center">
  <table>
    <tr>
      <td> <img src="src/main/resources/static/images/spring.svg" height="200"/></td>
      <td><img src="src/main/resources/static/images/apache_velocity.svg" height="200"/></td>
      <td><img src="src/main/resources/static/images/itext.svg" height="200" /></td>
    </tr>
  </table>
</p>

## Steps to deploy
- Just load this project on your preferred IDE e.g. Intellij or Netbeans ...etc and Maven will load all dependencies
  and configure directories.  
- This project uses an embedded "h2 database" so there's no need for a dbms e.g. MySQL or Postgres ...etc  

## Testing
- Provided with two testing templates, You can use Postman or something similar to test functionalities.
- Use the following POST request url.
```
http://localhost:8080/report/pdf
```  
- Note: Supported responses are [application/pdf, text/plain]. The second option is useful in cases when you need to return just
 a path for the generated pdf file {a performance-wise choice -> helps in some cases} so, you need to specify the returned response using the [Accept] HTTP header.  
 
 Example (1) Pets Report :
```

{
    "reportName": "Pets Report",
    "data": {
        "petList": [
            {
                "price": 800,
                "name": "Bold Eagle"
            },
            {
                "price": 0,
                "name": "My EX"
            }
        ]
    }
}

```
 Example (2) Employees Report :
```
{
    "reportName": "Employees Report",
    "data": {
        "employeesList": [
            {
                "salary": 10000,
                "name": "Ibrahim Osama",
                "joining_date": 1633694734587
            },
            {
                "salary": 15000,
                "name": "Muhammad Ali",
                "joining_date": 1633694734587
            },
            {
                "salary": 800,
                "name": "Mostafa Al-Sagher",
                "joining_date": 1633694734587
            },
            {
                "salary": 0,
                "name": "Rana Adel",
                "joining_date": 1633694734587
            },
            {
                "salary": 800,
                "name": "Sara Ahmed",
                "joining_date": 1633694734587
            },
            {
                "salary": 1200,
                "name": "Karim Raafat",
                "joining_date": 1633694734587
            }
        ],
        "reason": "Requested by the CEO.",
        "note": "From the fall of 2020.",
        "issued_by": "Mr. Ibrahim Osama.",
        "descriptionList": [
            {
                "description": "This data is discloused and not eligable to be shared outside of the organization."
            },
            {
                "description": "Refer to your manager if you find data outdated or somehow incorrect."
            },
            {
                "description": "Contact Dev Team (+201114388288) for any report UI issues."
            }
        ]
    }
}
```  

## Authors  
   **Muhammad Ali** - find me on : [LinkedIn](https://www.linkedin.com/in/zatribune).    
