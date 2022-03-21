# JFXSplash
a simple library to create splash screen for javafx applications

## Example

Create a custom splash screen

If you use `.setLayout(Parent root)`, the library will automatically create a custom splash screen.

if not, it will set the default.

```java
var loader = new FXMLLoader(getClass().getResource("splash-view.fxml"));
var splash = new JFXSplash.SplashBuilder(stage)
	.setDuration(Duration.seconds(3))
	.setLayout(loader.load())
	.setTitle("JFXSplashDemo")
	.setWidth(534)
	.setHeight(350)
	.build();
```
