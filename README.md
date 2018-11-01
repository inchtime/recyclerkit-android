RecyclerKit for Android
=======================

RecyclerKit is support to use android.support.v7.widget.RecyclerView to display thousands of layout you want via one adapter `RecyclerAdapter`.

RecyclerKit also provide a empty view that you can customized when the recyclerView is empty

Download
--------

Gradle:

```gradle
dependencies {
    implementation 'io.inchtime:recyclerkit:0.1.0'
}
```

Maven:

```xml
<dependency>
  <groupId>io.inchtime</groupId>
  <artifactId>recyclerkit</artifactId>
  <version>0.1.0</version>
  <type>pom</type>
</dependency>
```

How To Use
----------------------

Kotlin:

```kotlin
//LinearLayoutManager
val spanCount = 1
val adapter = RecyclerKit.adapter(this, spanCount)
    .recyclerView(R.id.recyclerView)
    .useLinearLayout(false)
    .build()
```

```kotlin
//GridLayoutManager
val spanCount = 2
val adapter = RecyclerKit.adapter(this, spanCount)
    .recyclerView(R.id.recyclerView)
    .useGridLayout()
    .build()
```

