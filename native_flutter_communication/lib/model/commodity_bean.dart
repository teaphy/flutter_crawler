import 'package:json_annotation/json_annotation.dart';

part 'commodity_bean.g.dart';


@JsonSerializable()
class CommodityBean extends Object {
	
	@JsonKey(name: 'name')
	String name;
	
	@JsonKey(name: 'price')
	String price;
	
	CommodityBean(this.name,this.price,);
	
	factory CommodityBean.fromJson(Map<String, dynamic> srcJson) => _$CommodityBeanFromJson(srcJson);
	
	Map<String, dynamic> toJson() => _$CommodityBeanToJson(this);
	
}