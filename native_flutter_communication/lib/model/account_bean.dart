import 'package:json_annotation/json_annotation.dart'; 
  
part 'account_bean.g.dart';


@JsonSerializable()
  class AccountBean extends Object {

  @JsonKey(name: 'name')
  String name;

  @JsonKey(name: 'mobile')
  String mobile;

  AccountBean(this.name,this.mobile,);

  factory AccountBean.fromJson(Map<String, dynamic> srcJson) => _$AccountBeanFromJson(srcJson);

  Map<String, dynamic> toJson() => _$AccountBeanToJson(this);

}

  
