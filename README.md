# react-native-android-notification-listener

React Native Android Notification Listener is a library that allows you to listen for status bar notifications from all applications. (Android Only)

[![npm version](https://badge.fury.io/js/react-native-android-notification-listener.svg)](https://badge.fury.io/js/react-native-android-notification-listener)

## Getting started

`$ yarn install react-native-android-notification-listener`

### Mostly automatic installation

`$ react-native link react-native-android-notification-listener`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.lesimoes.androidnotificationlistener.RNAndroidNotificationListenerPackage;` to the imports at the top of the file
  - Add `new RNAndroidNotificationListenerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-android-notification-listener'
  	project(':react-native-android-notification-listener').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-android-notification-listener/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-android-notification-listener')
  	```


## Usage
```javascript
import RNAndroidNotificationListener from 'react-native-android-notification-listener';

// To check if the user has permission
const status = await RNAndroidNotificationListener.getPermissionStatus()
console.log(status) // Result can be 'authorized' or 'denied'

// To open the Android configuration page, where the user can set the permissions
RNAndroidNotificationListener.requestPermission()

// To handle the notification received
const handleIncomingNotifications = notification => {
	const { app, title, text } = notification

	...
}

// To listen to all status bar notifications
RNAndroidNotificationListener.onNotificationReceived(handleIncomingNotifications)
```

For more details, se the `sample/` project in this repository