<img src="https://github.com/SypherEngine/SypherEngine/blob/main/media/branding/SypherEngineBanner.png">
<h1 align="center"><strong>A Simple and Lightweight Java Game Engine</strong></h1>

<div align="center">
  <a href="https://github.com/SypherEngine/SypherEngine/issues"><img alt="GitHub issues" src="https://img.shields.io/github/issues/SypherEngine/SypherEngine?style=for-the-badge"></a>
  <a href="https://github.com/SypherEngine/SypherEngine/stargazers"><img alt="GitHub stars" src="https://img.shields.io/github/stars/SypherEngine/SypherEngine?style=for-the-badge"></a>
  <a href="https://github.com/SypherEngine/SypherEngine/blob/main/LICENSE"><img alt="GitHub license" src="https://img.shields.io/github/license/SypherEngine/SypherEngine?style=for-the-badge"></a>
 <a href="https://dsc.gg/sypherengine"><img alt="Discord" src="https://img.shields.io/discord/933650222347857971?style=for-the-badge"></a>
</div>

<br>

<p align="center"><code>SypherEngine</code> is a simple game engine, made using pure Java, designed to be lightweight, fast and easy to use.</p> 

<h3 align="center"><strong>Disclaimer</strong></h3> 

> SypherEngine is currently in an alpha stage and is subject to many changes. While it does have basic features, there are many bugs still untracked and the documentation is currently a work in progress... If you wish to help, all help is appreciated!

<h1 align="center">:chains: Helpful links :chains:</h1>

 - [Website](https://sypherengine.github.io/)
 - [Tutorial (v0.5.0 onwards)](https://aurumbyte.blogspot.com/2022/08/lets-build-game-java-style-sypherengine.html)
 - [Docs (v0.4.0)](https://sypherengine.github.io/javadocs/SypherEngine/module-summary.html)
 - [Discord Community](https://dsc.gg/sypherengine)

<h1 align="center">🔨 Features 🔨</h1> 

- Rendering Framework
- Basic Event/Input Handling
- Entity Component System
- Scenes and Scene Switching
- Audio Engine
- Sprite rendering and Animations
- Logging
- 2D Camera

<h1 align="center">💻 Getting SypherEngine 💻</h1>

To use `SypherEngine`, please do make sure that you have the following requirements installed on your device.

- Java >= 17
- Maven (Only required for building the project from scratch)

If you do, then include the engine into your project as follows

### Gradle
```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}



	dependencies {
	        implementation 'com.github.SypherEngine:SypherEngine:Tag'
	}

```

### Maven
```xml
<repositories>
	<repository>	
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>

<!--In <dependencies>-->
<dependency>
  	<groupId>com.github.SypherEngine</groupId>
  	<artifactId>SypherEngine</artifactId>
  	<version>Tag</version>
</dependency>
```

The `Tag` is just the release tag.

### From Source

After downloading the source, build the engine, using `mvn clean package`.

Add it to your project classpath to start developing!

<h1 align="center">👋 SypherEngine HelloWorld 👋</h1>

Here's a small program to get started with `SypherEngine`...

```java
public class Main extends GameManager {

  //These can be left blank for this simple hello world program
  @Override
  public void init(SypherEngine engine){}
  
  @Override
  public void update(float deltaTime){}

  @Override
  public void render(Renderer renderer){}
  
  @Override
  public void dispose(){}

  public static void main(String[] args){
    SypherEngine.init(new Main(), "Hello, World!"); // Creating a window titled "Hello, World!"
    SypherEngine.run();
  }
}
```
<h1 align="center">📇 License and Code of Conduct 📇</h1>

This project follows the MIT license, refer [LICENSE](https://github.com/SypherEngine/SypherEngine/blob/main/LICENSE)
This project also follows the [Contributor Covenant Code of Conduct](https://github.com/SypherEngine/SypherEngine/blob/main/CODE_OF_CONDUCT.md)

<h1 align="center">🤝 Contributing 🤝</h1>

From the smallest change to a bug fix or feature implementation, Contributions are extremely welcome! Refer the [Contributing Guidelines](https://github.com/SypherEngine/SypherEngine/blob/main/CONTRIBUTING.md) for more info, and thank you for your help!

<h1 align="center">🫂 Support 🫂</h1>

This project is still new to the Open Source Community and we hope to make a good difference! If you like this project, consider giving it a star 🌟 to show your support. It helps a ton :)
