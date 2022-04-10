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
## Methods

|Syntax            |Description             |
| ---------------- | ---------------------- |
|setTitle(String title)|set title of stage and default splash screen label|
|setDuration(@NotNull Duration duration)|set duration of splash screen|
|setStage(@NotNull Stage stage)|set main stage to show after delay|
|setWidth(double width)|set width of splash screen|
|setHeight(double height)|set height of splash screen|
|addEventListeners(EventListener... listener)|event listener for start and finish the splash screen|
|setLayout(Parent root)|set custom view|
|addIcons(Image... images)|add icons to splash screen stage|
|build()|build and show|
|stop()|close splash screen if main stage not showing|
|getTitle()|get title of splash screen|
|getDuration()|get duration of splash screen|
|getWidth()|get width of splash screen|
|getHeight()|get height of splash screen|
|getLayout()|get applied custom view|
|getListeners()|get list of event listeners|
|currentTimeProperty()|get current time property at runtime|
|getStage()|get splash screen window|
