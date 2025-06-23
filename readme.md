# The COGIP API

## The Mission

The **COGIP**, a company both supposedly humengous yet completly obscure to the general public. They produce the [Jean-Michel toys](http://dai.ly/x3a51), do [Pokemon exchanges](https://www.dailymotion.com/video/x68i87) and have incredible [employees](https://www.dailymotion.com/video/xabck6) within their rank. In sum they are the perfect corporate environment for some whacky Java development. Get ready to lose some hair as this challenge will have you **create** an **API** as well as a **CLI user interface** for _Jean-Christian Ranu_ who works in accounting.

Until now the COGIP has had a [monolithic software](https://en.m.wikipedia.org/wiki/Monolithic_application) architecture to manage its accounting, it has become clear however that this way of doing things was limiting. Indeed different branches of the compagny have different needs when it comes to interfacing with the accounting data. In response to that desire to **separate data and interface**, the board as decided to allow the development of a prototype [microservice architecture](https://en.m.wikipedia.org/wiki/Microservices) and you are to be in charge of the project!

First things first, you should begin by creating a repository for the **REST API** meant to serve the data. It's important that **only code related to the API** exist within this repo. From there you will have to use [this SQL file](./assets/accounting.sql) as the data (_structure_ + _some base data_) to manage through your API. The API itself must be able to serve content in `JSON` or modify entries in the database depending on the following roles:

- **admin**: read and write access to everything
- TODO

Furthermore, and in order to convince the board to invest more development time into the project, you also need to develop **at least one interface** (_in its own repository_) to the API. Since the primary concerned will be _Mr. Ranu_ and given the fact that he recently, and magically, learned some scripting, you should aim for a non-interactive (_subcommands and flags only_) CLI interface with the following features:

- A way to "_login_", effectively loading the user's role into the environment.
- A way to **add**, **list**, **modify** or **delete** ([CRUD](https://en.m.wikipedia.org/wiki/Create,_read,_update_and_delete)) _companies_, _invoices_, _contacts_ and _users_.
- A way to display only the companies of a certain type (_provider_ or _client_)
- _companies_ should always be listed with at least the following informations: name of the company, VAT number of the company, list of invoices linked to the company, list of contacts working for the company.
- _invoices_ should be listed with the following informations: number, date, company linked to the invoice, type of company (_provider_ or _client_), contact linked to the invoice.
- _contacts_ should be listed with the following informations: first and last name, email, name of the company where the person works, list of all the linked invoices.
- A way to **select** a specific item (_company_, _user_, _invoice_, _contact_) to be displayed.
- User should have the choice to listed the data in either a "_pretty_" (_human readable_) or `JSON` format.

It is paramount and definitely a **must have** that both codebases (_API_ and _interface_) be managed and automated via `Maven` as well as have a full suite of `unit tests`.

**IMPORTANT**: A lot of "_artistic freedom_" is given when it comes to the actual implementation, it's on purpuse. Part of the exercise is to force you to think the tool you design through. For instance, many of the features listed in the CLI interface **should be made easier** by thinking through the data that the API actually serves.

## Complementary Resources

- **ARTICLE**: [What are microservices?](https://stackify.com/what-are-microservices/)
- **ARTICLE**: [Microservices vs. monolithic architecture](https://www.atlassian.com/microservices/microservices-architecture/microservices-vs-monolith#:~:text=A%20monolithic%20application%20is%20built,of%20smaller%2C%20independently%20deployable%20services.)
- **THREAD**: [What does non-interactive command line tool means?](https://stackoverflow.com/questions/16456857/what-does-non-interactive-command-line-tool-means)

## Expected Output

At the end of this challenge you should be [able to use Java to write a modern microservice application](./evaluation.md), which should be verifiable by **two repositories** each one containing one of the codebases of this project.
