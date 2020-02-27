import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class MethodChannelPage extends StatefulWidget {
	MethodChannelPage({Key key}) : super(key: key);
	
	@override
	_MethodChannelPageState createState() => _MethodChannelPageState();
}
class _MethodChannelPageState extends State<MethodChannelPage> {
	
	static const platform = const MethodChannel('com.example.native_flutter_communication/methodChannelAty');
	var resultNative  = "";
	
	@override
	Widget build(BuildContext context) {
		return Scaffold(
			appBar: AppBar(
				title: Text("MethodChannel"),
			),
			body: Center(
				child: Column(
					mainAxisAlignment: MainAxisAlignment.center,
					crossAxisAlignment: CrossAxisAlignment.center,
					children: <Widget>[
						Text("Native Result: $resultNative"),
						FlatButton(
							onPressed: _gotoMethodChannelActivity,
							child: Text("goto MethodChannelActivity"),
						)
					],
				),
			),
		);
	}

  void _gotoMethodChannelActivity() async {
	  var result;
		try{
			result = await platform.invokeMethod("methodChannelAty");
		} on PlatformException catch (e) {
			result = "Failed to get result from Native";
		}
		 setState(() {
		   resultNative = result;
		 });
  }
}