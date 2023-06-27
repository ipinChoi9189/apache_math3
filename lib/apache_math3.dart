import 'apache_math3_platform_interface.dart';

class ApacheMath3 {
  Future<String?> getPlatformVersion() {
    return ApacheMath3Platform.instance.getPlatformVersion();
  }

  Future<List<double>> linearErp({
    required List<int> input,
    required List<double> value,
    required List<int> output,
  }) async =>
    ApacheMath3Platform.instance
        .linearErp(input: input, value: value, output: output);

}
