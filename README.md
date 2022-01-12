<img src="https://github.com/SypherEngine/SypherEngine/blob/readme/logos/SypherEngineBanner.svg">
<h1 align="center"><strong>A Simple and Lightweight Java Game Engine</strong></h1>

<p align="center"><code>SypherEngine</code> is a simple game engine, made using pure Java, designed to be lightweight, fast and easy to use.</p> 

<h1 align="center">🔨 Features 🔨</h1> 

- Rendering Framework
- Entity Component System
- Scenes and Scene Switching
- Audio Engine
- Sprite rendering and Animations
- Lighting and Shadows
- 2D Camera 

<h1 align="center">💻 Getting SypherEngine 💻</h1>

To use `SypherEngine`, please do make sure that you have the following requirements installed on your device.

- Java 16 or greater
- Maven

<!--
If you do, then include the engine into your project as follows

### Maven
```xml

```
-->

After downloading the source, build the engine, using `mvn clean package`.

<h1 align="center">👋 SypherEngine HelloWorld 👋</h1>

Here's a small program to get started with `SypherEngine`...

```java
public class Main extends GameManager<Main> {

  //These can be left blank for this simple hello world program
  @Override
  public void init(){
    //init your game
  }
  
  @Override
  public void update(SypherEngine engine, float deltaTime){
    //updating
  }

  @Override
  public void render(SypherEngine engine, Renderer renderer){
    //rendering
  }

  public static void main(String[] args){
    SypherEngine engine = new SypherEngine(new Main()); //initializes the engine with the main game class
    engine.setTitle("HelloWorld!"); //sets 
    engine.start();
  }
}
```
<h1 align="center">📇 License 📇</h1>

This project follows the MIT license, refer [LICENSE.txt]()

<h1 align="center">🤝 Contributing 🤝</h1>

From the smallest change to a bug fix or feature implementation, Contributions are extremely welcome! Refer the [Contributing Guidelines]() for more info, and thank you for your help!

<h1 align="center">🫂 Support 🫂</h1>

This project is still new to the Open Source Community and we hope to make a good difference! If you like this project, consider giving it a star 🌟 to show your support. It helps a lot.
