# Book Address App

CLI to execute .

[![Language](https://img.shields.io/badge/linguagem-Java-red)](https://www.python.org/)
[![License](https://img.shields.io/badge/license-MIT-yellow)](https://opensource.org/licenses/MIT)
[![License](https://img.shields.io/badge/repository-git-white)](https://github.com/alexncosta/addressbookapp.git)

## Prerequisites:
* Java 21
* Maven 3.6.3+

## ✨ Features:
- [x] How many males are in the address book?
- [x] Who is the oldest person in the address book?
- [x] How many days older is Bill than Paul?

## 🔨 How can you execute:
**Clone repository**<br />
```
$ git clone https://github.com/alexncosta/addressbookapp.git
```
<br />
In the root directory of the project, execute the following command:<br />
<b>mvn clean install</b>

After the execution of the command, the project will be compiled and the tests will be executed. 
If everything is correct, a message similar to the one shown below will be displayed:
```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.210 s
[INFO] Finished at: 2025-01-28T16:43:08-03:00
[INFO] ------------------------------------------------------------------------
```
<br />
<b>Go to the target directory and execute the following commands:</b><br />

```
cd target/
java -jar AddressBook-1.0-SNAPSHOT.jar
```
After execute the last command, the application will be executed and will display the following:<br />
Enter the first person's name to calculate the difference age between tow persons:<br />
<b>Type the first name</b><br />

```
Bill McKnight
```
<b>Press Enter</b><br />

<b>Will be displayed the following text:</b><br />
Enter the second person's name to calculate the difference age between tow persons:<br />
<b>Type the second name</b><br />
```
Paul Robinson
```
<b>Press Enter</b><br />

<b>At last, will be displayed the following result:</b><br />
```
Number of Males: 3
The oldest person: Wes Jackson
The age difference: 2862
```
