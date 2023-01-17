# kryo-more

Kryo 5.x serializers for various libGDX-related libraries.

This lets Kryo 5.x (currently 5.4.0) de/serialize objects from [RegExodus](https://github.com/tommyettinger/RegExodus),
[digital](https://github.com/tommyettinger/digital), [jdkgdxds](https://github.com/tommyettinger/jdkgdxds),
[juniper](https://github.com/tommyettinger/juniper), and [simple-graphs](https://github.com/earlygrey/simple-graphs).
All of these but simple-graphs are maintained by [tommyettinger](https://github.com/tommyettinger), who also maintains
this serialization library.

## How to get

Each sub-library has its own version, linked to the version of the library it de/serializes.

  - kryo-regexodus is currently version 0.1.14.0
  - kryo-digital is currently version 0.1.7.0
  - kryo-jdkgdxds is currently version 1.1.1.0
  - kryo-juniper is currently version 0.1.7.0
  - kryo-simple-graphs is currently version 5.1.1.0

Gradle dependency info:

kryo-regexodus:

```gradle
implementation "com.github.tommyettinger:kryo-regexodus:0.1.14.0"
```

kryo-digital:

```gradle
implementation "com.github.tommyettinger:kryo-digital:0.1.7.0"
```

kryo-jdkgdxds:

```gradle
implementation "com.github.tommyettinger:kryo-jdkgdxds:1.1.1.0"
```

kryo-juniper:

```gradle
implementation "com.github.tommyettinger:kryo-juniper:0.1.7.0"
```

kryo-simple-graphs:

```gradle
implementation "com.github.tommyettinger:kryo-simple-graphs:5.1.1.0"
```

Maven dependency info:

kryo-regexodus:

```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>kryo-regexodus</artifactId>
  <version>0.1.14.0</version>
</dependency>
```

kryo-digital:

```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>kryo-digital</artifactId>
  <version>0.1.7.0</version>
</dependency>
```

kryo-jdkgdxds:

```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>kryo-jdkgdxds</artifactId>
  <version>1.1.1.0</version>
</dependency>
```

kryo-juniper:

```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>kryo-juniper</artifactId>
  <version>0.1.7.0</version>
</dependency>
```

kryo-simple-graphs:

```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>kryo-simple-graphs</artifactId>
  <version>5.1.1.0</version>
</dependency>
```

## License

Apache 2.0, [see the LICENSE file](LICENSE).