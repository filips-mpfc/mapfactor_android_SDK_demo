# Mapfactor Android SDK demo application
This repo serves as a guide for SDK initialization
Example failed initalization           |  Successful init
:-------------------------:|:-------------------------:
![failed_activation_sdk](https://github.com/user-attachments/assets/2756857e-faa7-4ad6-9e1f-fc022bc11e76) | ![success_activation](https://github.com/user-attachments/assets/824ea642-03ff-4245-a7f4-ab49bbf1876d)






## Updated info
Check [sdk.mapfactor.com](https://sdk.mapfactor.com) for the latest info.

## Components
- Jetpack Compose
- ViewModels
- Dagger Hilt dependency injection

## Minimal requirements
- Android Studio
- Android device or emulator running API 23 and above
- Since this is an __offline__ maps-based SDK, sufficient space on internal or external storage is required
If you encounter any issue, please check the Logcat first, it mays help to find the cause.

## What you need
- As you can see, the [gradle.properties](gradle.properties) contains credentials to download the library from our Maven repository. Please ask our [sales@mapfactor.com](mailto:sales@mapfactor.com).
- SDK initialisation needs to insert the licence key to activate the device ([SDKManager.kt](app/src/main/java/com/example/followtheguidesdk/util/SDKManager.kt)), please ask our [sales department](sales@mapfactor.com) for it. Possiblity to create test key for limited amount of activations.





