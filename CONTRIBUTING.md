# SypherEngine Contributing Guidelines

Hi there! And thanks for your interest in the growth of this project! Here a few guidelines to follow whilst contributing...

## General Guidelines

- If you intend on making changes to the engine's source, forking this repository is recommended.

- If you have a feature in mind, please [open an issue](https://github.com/SypherEngine/SypherEngine/issues/new?assignees=&labels=enhancement&template=feature_request.md&title=%5BFeature+Request%5D) discussing it. After a green light, you can fork this repository, add your changes and open a pull request describing your changes.

- If you wish to tackle an existing issue, comment on that particular issue stating your interest to work on it. This is done to prevent multiple people from separately working on the same issue, leading to problems... if a collaborator or maintainer approves, go right ahead!

- If you have used this engine and have found a bug, please report it by opening a [bug report](https://github.com/SypherEngine/SypherEngine/issues/new?assignees=&labels=bug&template=bug_report.md&title=%5BBug%5D). If you wish to fix it, comment on that issue and it can be assigned to you.

- Last but not least, please follow proper code formatting as per the current code base, it keeps the code consistent and clean.

## Code formatting

#### Control Statement Formatting
```java
for(int i = 0; i < 100; i++){
  if(i == 100){
  
  }
}

//or

for(int i = 0; i < 100; i++) {
  if(i == 100) {
  
  }
}
```

#### Method Formatting
```java
public static void methodName(String param1, int param2){

}

// or

public static void methodName(String param1, int param2) {

}
```

#### Long conditions
```java
if(condition1 //could be longer, i just used "condition1..." for brevity
&& condition2 
|| condition3 
|| something > anotherthing)
{
  //code here
}
```

#### Multiple Arguments / Long Arguments
```java
//actual working code as an example (drawing a square to center of the screen (almost))

engine.getRenderer().drawRectangle(
  engine.getRenderer().getScreenCenter().getX(),
  engine.getRenderer().getScreenCenter().getY(),
  50,
  50,
  true //fill in the square
  Color.BLUE
);
```

### Switch statements
```java
//use the new enhanced switch cases in Java
switch(something){
  case FIRSTCASE -> one line; //if return statement or direct break
  case NEXTCASE -> {
    //multiple statements
  }
}
```

> Tabs are 4 spaces

#### Naming

 - Methods : camelCase
 - Fields : camelCase
 - Variables : camelCase
 - Classes : PascalCase
 - Enum members : ALLCAPS or ALL_CAPS_SNAKE_CASE
 - Final Variables : ALLCAPS or ALL_CAPS_SNAKE_CASE

## Setting up SypherEngine for development

- Download the source or clone the repo using:
```
git clone https://github.com/SypherEngine/SypherEngine.git
```
- Build the engine using `mvn clean package` 
