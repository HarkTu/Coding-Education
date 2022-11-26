# **Leasing Software**

___
#
## _Spring boot project with spring security and Oracle DB_

>This software works with Http requests. Processes all received data and then stores it
in the database where is organized and easy to find, such as customers, leases, requests for leases, personal information and etc.
#
___
## Features

- Registrate or login in the system.
- You can set your personal information.
- Change profile details like username, password, or email.
- Evrithing is protected becouse of **Spring security**.
- View your profile and your personal information.
- Apply for a lease and the software will calculate your credit risk.
- Automatically create payment plan when the leasing is approved.
- Pay leases with installment amount or bigger.
- Payment plans are changing everytime when client pay with different amount.
- Admins can get all requested and approved leases.
- They can see clients credit rating and approve requested leases.
#
___

> Some methods are authorized and not everyone have access to them.
> The functionality is filtered for admins and clients.
> All information that comes from outside will be
validated  before get in the system and push in database.
___

## Tech

**This leasing system uses a number of  projects to work properly:**

- Spring Security
- Maven
- Oracle SQL Dev.
- InteliJ IDEA

___

**You can clone repository fom GitLab to see more about the code.**

## Installation

* Requires [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* Setup Java version 8
* Install the dependencies and start the server.

```
cd existing_repo
git remote add origin https://hepta-acad.codixfr.private/group-projects/final-projects-1/team-2-rusi
git branch -M branch/default
git push -uf origin branch/default
```

## Development

> We will add a function to send emails to remind the clients
that they need to pay their bill and send emails to prove themselves
when they make new registrations.

# Our team
```
 Tsvetelin Enchev
 Zlatina Ivanova
 Shtilyan Karamfilov
 Borislav Pavlov
```