import 'apache_math3_platform_interface.dart';

class ApacheMath3 {
  Future<String?> getPlatformVersion() {
    print('sisisisibal');
    return ApacheMath3Platform.instance.getPlatformVersion();
  }

  Future<List<double>?> linearErp({
    required List<int> input,
    required List<double> value,
    required List<int> output,
  }) async {
    print(
        'cvvcccccccc input: ${input.length} value: ${value.length} output:${output.length}');
    List<double> temp = await ApacheMath3Platform.instance
        .linearErp(input: input, value: value, output: output) ?? [];
    print('tttttttttt');
    print(temp);
    return Future.value(temp);
  }
}
