Before you run the java formatter, you need to download the formatter jar to this directory:
```
curl -sSLo formatter.jar https://github.com/google/google-java-format/releases/download/v1.23.0/google-java-format-1.23.0-all-deps.jar
```

To run the formatter, run this command from the project root directory (point the config to this folder)
```
dprint fmt --config=fmt/dprint.json
```
