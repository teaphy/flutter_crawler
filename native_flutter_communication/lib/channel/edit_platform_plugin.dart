import 'dart:convert';

import 'package:flutter/services.dart';
import 'package:native_flutter_communication/channel/word_platform_plugin.dart';
import 'package:native_flutter_communication/const/account_const.dart';

const MethodChannel editMethodChannel = MethodChannel("com.example.native_flutter_communication/edit");

Future<dynamic> editChannelHandler(MethodCall methodCall) async {
	switch (methodCall.method) {
		case 'getAccountData':
			return json.encode(AccountConst.accountBean.toJson());
		case "sendWordData":
			sendWord();
			break;
		default:
		// todo - throw not implemented
	}
}
