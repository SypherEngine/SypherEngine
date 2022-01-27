<img src="https://github.com/SypherEngine/SypherEngine/blob/main/logos/SypherEngineBanner.svg">
<h1 align="center"><strong>A Simple and Lightweight Java Game Engine</strong></h1>

<p align="center"><code>SypherEngine</code> is a simple game engine, made using pure Java, designed to be lightweight, fast and easy to use.</p> 

<h3 align="center"><strong>Disclaimer</strong></h3> 

> SypherEngine is currently in an alpha stage and is subject to many changes. While it does have basic features, there are many bugs still untracked and the documentation is currently a work in progress... If you wish to help, all help is appreciated!

<h1 align="center">🔨 Features 🔨</h1> 

- Rendering Framework
- Entity Component System
- Scenes and Scene Switching
- Audio Engine
- Sprite rendering and Animations

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
public class Main extends GameManager {

  //These can be left blank for this simple hello world program
  @Override
  public void init(){
    //init your game
  }
  
  @Override
  public void update(float deltaTime){
    //updating
  }

  @Override
  public void render(Renderer renderer){
    //rendering
  }

  public static void main(String[] args){
    SypherEngine.init(new Main());
    SypherEngine.run();
  }
}
```
<h1 align="center">📇 License 📇</h1>

This project follows the MIT license, refer [LICENSE](https://github.com/SypherEngine/SypherEngine/blob/main/LICENSE)

<h1 align="center">🤝 Contributing 🤝</h1>

From the smallest change to a bug fix or feature implementation, Contributions are extremely welcome! Refer the [Contributing Guidelines](https://github.com/SypherEngine/SypherEngine/blob/main/CONTRIBUTING.md) for more info, and thank you for your help!

<h1 align="center">🫂 Support 🫂</h1>

This project is still new to the Open Source Community and we hope to make a good difference! If you like this project, consider giving it a star 🌟 to show your support. It helps a lot.
