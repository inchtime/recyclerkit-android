RecyclerKit for Android
=======================

RecyclerKit is support to use android.support.v7.widget.RecyclerView to display thousands of layout you want via one adapter `RecyclerAdapter`.

RecyclerKit also provide a empty view that you can customized when the recyclerView is empty

Download
--------

Gradle:

```gradle
dependencies {
    implementation 'io.inchtime:recyclerkit:0.5.0'
}
```

Maven:

```xml
<dependency>
  <groupId>io.inchtime</groupId>
  <artifactId>recyclerkit</artifactId>
  <version>0.5.0</version>
  <type>pom</type>
</dependency>
```

How To Use
----------------------

**Kotlin:**

```kotlin
//LinearLayoutManager
val spanCount = 1
val adapter = RecyclerKit.adapter(this, spanCount)
    .recyclerView(R.id.recyclerView)
    .withLinearLayout()
    .build()
```

```kotlin
//GridLayoutManager
val spanCount = 2
val adapter = RecyclerKit.adapter(this, spanCount)
    .recyclerView(R.id.recyclerView)
    .withGridLayout()
    .build()
```

the RecyclerView will show the empty view.
then we can add items to the `adapter`:

```kotlin
val models = ArrayList<RecyclerAdapter.ViewModel>()

models.add(
  RecyclerAdapter.ViewModel(R.layout.view_examples_icon,
  1,  
  RecyclerAdapter.ModelType.MIDDLE, 
  Pair(R.drawable.icon_appstore, getString(R.string.app_store))
  )
)

adapter.addItems(models)
```

At last, bind the view model:

```kotlin
adapter.onModelViewBind = { _, viewModel, viewHolder ->
    when (viewModel.layout) {
        R.layout.view_examples_icon -> {
            val pair = viewModel.value as Pair<*,*>
            val icon = pair.first as Int
            val title = pair.second as String
            val iconImageView = viewHolder.findView<ImageView>(R.id.iconImageView)
            val titleTextView = viewHolder.findView<TextView>(R.id.titleTextView)
            iconImageView.setImageResource(icon)
            titleTextView.text = title
        }
    }
}
```

Detail [example][1]


Author
------

Evan Cai

QQ: 214390117

email: evan-cai@live.cn

License
-------

Apache 2.0. See the [LICENSE][2] file for details.

[1]: https://github.com/inchtime/recyclerkit-android/blob/master/app/src/main/java/io/inchtime/recyclerkit/example/MainActivity.kt
[2]: https://github.com/inchtime/recyclerkit-android/blob/master/LICENSE