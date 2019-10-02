# TabcorpTest
 
## Architecture

The overriding principle was KISS. This is a piece of demo code, so I've not given any thoughts to extensibility or reuse. Instead it's a quick and dirty app.

In terms of patterns, I'm using a basic MVC or MVP to display data in the main activity. The JSON is hidden behind a facade.

## Unit Tests

There aren't any. I have written them before, and can supply sample code. I wanted to turn this around as quick as possible so I skipped them.

## Documentation

This is incomplete. Again, I'm cutting corners so didn't Javadoc (or whatever the Kotlin equivalent is called) all methods and variables.

## UI Design

There's none. If you want examples of my work, take a look at the Tab QLD wagering app. I did a few screens in that.

## TODO

The rocket details aren't being displayed in the details page. To do this I need to add a few text views, and then a line like:

```kotlin
rocket_name.text = rocket.getName()
```

That's pretty straightforward, and you can probably live without seeing it.
