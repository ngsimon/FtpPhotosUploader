# FtpPhotosUploader
A tool that allows you to upload photos directly from your camera to an FTP server. This runs on Sony cameras using the [Sony-PMCA-RE framework](https://github.com/ma1co/Sony-PMCA-RE).

# Changes

## 2024/09/05
- Added the options "Upload Raw" and "Upload JPG" to select which kind of file to upload.
- Removed the increase buffer size (it makes ILCE7 crashing).
- Added the option to only upload the videos/pictures taken on the same day.

# Rebuild note
```
keytool -genkey -v -keystore my-release-key.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias my-key-alias
/opt/android-sdk/build-tools/27.0.3/apksigner sign --ks my-release-key.keystore --out signed-app.apk app-release-unsigned.apk
```
