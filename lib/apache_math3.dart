import 'package:flutter/services.dart';

class ApacheMath3 {
  static const MethodChannel _channel = MethodChannel('apache_math3');

  static Future<String?> getPlatformVersion() async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  Future<List<double>?> linearErp({
    required List<int> input,
    required List<double> value,
    required List<int> output,
  }) async {
    final result = await _channel.invokeMethod(
        'linearErp', {"input": input, "value": value, "output": output});
    return result;
  }

  // Future<String?> getPlatformVersion() {
  //   return ApacheMath3Platform.instance.getPlatformVersion();
  // }
  //
  // Future<List<double>?> linearErp({
  //   required List<int> input,
  //   required List<double> value,
  //   required List<int> output,
  // }) async {
  //   return await ApacheMath3Platform.instance
  //           .linearErp(input: input, value: value, output: output) ??
  //       [];
  // }
}
