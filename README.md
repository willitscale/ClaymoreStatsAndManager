# ClaymoreStatsAndManager

Set-up
Copy the example config `src/main/resources/config.example.properties` to `src/main/resources/config.properties` and add your settings.

This is a primitive set-up, so you need to set the `rigs` property to equal the amount of rigs you have in the properties.

To build (bit of a hack to execute the build):
```
gradlew.bat -PmainClass=uk.co.n3tw0rk.claymorestats.Main classes # Windows
./gradlew -PmainClass=uk.co.n3tw0rk.claymorestats.Main classes # Linux
```

Then to run simply execute:
```
gradlew.bat -PmainClass=uk.co.n3tw0rk.claymorestats.Main execute # Windows
./gradlew -PmainClass=uk.co.n3tw0rk.claymorestats.Main execute # Linux
```
