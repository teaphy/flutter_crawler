import 'package:english_words/english_words.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'channel/edit_platform_plugin.dart';
import 'channel/word_platform_plugin.dart';

class RandomWordPage extends StatefulWidget {
	RandomWordPage({Key key}) : super(key: key);
	
	@override
	_RandomWordPageState createState() => _RandomWordPageState();
}

class _RandomWordPageState extends State<RandomWordPage> {
	static const platformAccount =
	const MethodChannel('com.example.native_flutter_communication/account');
	
	var listData = List<int>();
	
	@override
	void initState() {
		super.initState();
		_subscribeMessage();
		
		_notifyNative();
	}
	
	@override
	Widget build(BuildContext context) {
		return Scaffold(
			appBar: AppBar(
				title: Text("RandomWord"),
			),
			body: ListView.builder(
					itemExtent: 48,
					itemCount: listData.length,
					itemBuilder: (context, index) =>
							ListTile(
								title: Text(all[listData[index]]),
							)),
		);
	}
	
	void _subscribeMessage() {
		randomMessageChannel.setMessageHandler(_handleMessage);
	}
	
	Future _handleMessage(dynamic message) async {
		String reply;
		if (message is int) {
			setState(() {
				listData.add(message);
				reply = "已经收到 message; $message ";
			});
		} else {
			reply = "RandomMessageChannel只接收int类型的消息 ";
		}
		
		return reply;
	}
	
	// 通知原生发射数据
	void _notifyNative() async {
		platformAccount.invokeMethod("notifySendRandom");
	}
}
