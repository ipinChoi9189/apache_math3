package com.ipinlabs.apache_math3;

import androidx.annotation.NonNull;

import java.util.List;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.util.ArrayList;
import java.util.Arrays;

/** ApacheMath3Plugin */
public class ApacheMath3Plugin extends FlutterActivity {

  private static final String TAG = "ApacheMath3Channel";

  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    super.configureFlutterEngine(flutterEngine);
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    final MethodChannel channel = new MethodChannel(flutterEngine.getDartExecutor(), "apache_math3");
    channel.setMethodCallHandler(handler);
  }

  private MethodChannel.MethodCallHandler handler = (methodCall, result) -> {
    if (methodCall.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }else if(methodCall.method.equals("linearErp")) {
      try {
        List<Integer> t_in = methodCall.argument("input");
//      List<Double> originValue = call.argument("value");
        List<Integer> originOutput = methodCall.argument("output");

        List<Long> longTinList = new ArrayList<>();
        for (Integer inElement : t_in) {
          longTinList.add(inElement.longValue());
        }
        List<Double> originValue = methodCall.argument("value");
        List<Double> tempValueList = new ArrayList<>(originValue);
        List<Float> floatValue = new ArrayList<>();
        for (Double element : originValue) {
          floatValue.add(element.floatValue());
        }

        long[] t_out = new long[originOutput.size()];
        for (int i = 0; i < originOutput.size(); i++) {
          t_out[i] = originOutput.get(i).longValue();
        }

        LinearInterpolator li = new LinearInterpolator();
        PolynomialSplineFunction psf =
                li.interpolate(longTinList.stream().mapToDouble(Long::doubleValue).toArray(),
                        floatValue.stream().mapToDouble(Float::doubleValue).toArray());

        double[] temp = Arrays.stream(t_out).mapToDouble(psf::value).toArray();
        result.success(temp);

        System.out.println("11: success" + temp.length);

      } catch (Error e) {
        System.out.println("e:" + e.getMessage());
        result.error("error", e.getMessage(),null);
      }
    }else {
      result.notImplemented();
    }
  };

}
