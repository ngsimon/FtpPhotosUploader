all:
	./gradlew build
	test -f my-release-key.keystore || keytool -genkey -v -keystore my-release-key.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias my-key-alias -dname "CN=Your Name, OU=Your Organizational Unit, O=Your Organization, L=Your City, ST=Your State, C=Your Country" -storepass changeit -keypass changeit
	/opt/android-sdk/build-tools/27.0.3/apksigner sign --ks my-release-key.keystore --ks-key-alias my-key-alias --ks-pass pass:changeit --key-pass pass:changeit --out signed-app.apk app/build/outputs/apk/release/app-release-unsigned.apk

clean:
	rm -f signed-app.apk
	./gradlew clean



