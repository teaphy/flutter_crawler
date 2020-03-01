import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:native_flutter_communication/model/commodity_bean.dart';
import 'channel/commodity_event_channel.dart';

class CommodityPage extends StatefulWidget {
	CommodityPage({Key key}) : super(key: key);
	
	@override
	_CommodityPageState createState() => _CommodityPageState();
}

class _CommodityPageState extends State<CommodityPage> {
	static const platformAccount =
	const MethodChannel('com.example.native_flutter_communication/account');
	
	var listData = List<CommodityBean>();
	
	@override
	void initState() {
		// TODO: implement initState
		super.initState();
		_subscribeReceiver();
	}
	
	@override
	Widget build(BuildContext context) {
		return Scaffold(
			appBar: AppBar(
				title: Text("CommodityPage"),
				actions: <Widget>[
					IconButton(
						onPressed: _gotoAddCommodity,
						icon: Icon(Icons.add),
					)
				],
			),
			body: ListView.builder(
					itemCount: listData.length, itemBuilder: _generateCommodityItem),
		);
	}
	
	Widget _generateCommodityItem(BuildContext context, int index) {
		var bean = listData[index];
		return Column(
			mainAxisAlignment: MainAxisAlignment.center,
			crossAxisAlignment: CrossAxisAlignment.center,
			children: <Widget>[
				Padding(
					padding: EdgeInsets.symmetric(vertical: 14),
					child: Text("name: ${bean.name}"),
				),
				Padding(
					padding: EdgeInsets.symmetric(vertical: 14),
					child: Text("price: ${bean.price}"),
				),
			],
		);
	}
	
	void _subscribeReceiver() async {
		commodityEventChannel
				.receiveBroadcastStream("commity page subscribe")
				.listen((data) {
			if (data is String) {
				Map map = json.decode(data);
				var bean = CommodityBean.fromJson(map);
				setState(() {
					listData.add(bean);
				});
			}
		});
	}
	
	void _gotoAddCommodity() {
		platformAccount.invokeMethod("gotoAddCommodity");
	}
}
