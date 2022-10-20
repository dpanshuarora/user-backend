user-backend application is used to store and retrieve user data.

# API endpoints

These endpoints allow you to track and persist user locations.

## GET
 [/user-management/user/locations?userId={userId}&dateTimeStart={dateTimeStart}&dateTimeEnd={dateTimeEnd}](#get-/user-management/user/locations) <br/>
 [/user-management/user/latest-location?userId={userId}](#get-/user-management/user/latest-location?userId={userId}) <br/>

## POST

 [/user-management/user](#post-/user-management/user) <br/>
___

### GET /user-management/user/locations?userId={userId}&dateTimeStart={dateTimeStart}&dateTimeEnd={dateTimeEnd}
Get user locations filtered by datetime range

**Parameters**

|          Name | Required |  Type   | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|     `userId` | required | string  | The user id of the user for which locations need to be retrieved |
|     `dateTimeStart` | required | timestamp  | The start date time to filter by                                                                     |
|     `dateTimeEnd` | required | timestamp  | The end date time to filter by                                                                     |

**Response**

```
// user id with 2 locations
{
    "userId": "ab",
    "locations": [
        {
            "createdOn": "2022-01-05T22:35:06.000+00:00",
            "location": {
                "latitude": 93.25700491800492,
                "longitude": 9.528306568625961
            }
        },
        {
            "createdOn": "2022-01-03T23:35:06.000+00:00",
            "location": {
                "latitude": 94.25700491800492,
                "longitude": 9.528306568625961
            }
        }
    ]
}
```
___

### GET /user-management/user/latest-location?userId={userId}
Get user locations filtered by datetime range

**Parameters**

|          Name | Required |  Type   | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|     `userId` | required | string  | The user id of the user for which the data and latest location need to be retrieved |

**Response**

```
// user id with latest location
{
    "userId": "2e3b11b0-07a4-4873-8de5-d2ae2eab26b2",
    "createdOn": "2022-06-09T22:35:06.000+00:00",
    "email": "abc@gmail.com",
    "firstName": "John",
    "secondName": "Doe",
    "location": {
        "latitude": 50.25700491800493,
        "longitude": 15.528306568625961
    }
}
```
___

### POST /user-management/user
Saves or Updates a user.

**Parameters**

|          Name | Required |  Type   | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|     `userId` | required | string  | the user id for which to store the user data.                                                                     |
|        `createdOn` | required | timestamp  | the creation timestamp. |
| `email` | required | string | user email id.                    |
|       `firstName` | required | string  |           |
|    `secondName` | required | string  |           |

**Response**

```
201 CREATED
```


## IDEA setup

- Install the Lombok Plugin by going to Preferences -> Plugins and search by lombok.
- Check the "Enable annotation processing" checkbox under Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processors


## Build:

The command below will trigger unit, integration tests besides compiling the jar for the app:

```
./gradlew clean build
```

### Running the app and its dependencies

Run the main class from you IDE, or from the command line:

```
./gradlew bootRun
```
