package com.ipinlabs.apache_math3;

import androidx.annotation.NonNull;

import java.util.List;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.util.ArrayList;
import java.util.Arrays;

/** ApacheMath3Plugin */
public class ApacheMath3Plugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  private static final String TAG = "ApacheMath3Channel";

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "apache_math3");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }else if(call.method.equals("linearErp")) {
      System.out.println("start linerErp");
      try {
        List<Integer> t_in = call.argument("input");
        System.out.println("1: tin");
//      List<Double> originValue = call.argument("value");
        List<Integer> originOutput = call.argument("output");
        System.out.println("2: originOutput");

        assert t_in != null;
        System.out.println("3");

        assert originOutput != null;
        System.out.println("4");

        List<Long> longTinList = new ArrayList<>();
        for (Integer inElement : t_in) {
          longTinList.add(inElement.longValue());
        }
        System.out.println("5: logTin");

        List<Double> tempValueList = new ArrayList<>();
        List<Double> originValue = call.argument("value");
        System.out.println("6: origin value");
        assert originValue != null;
        for (double element : originValue) {
          tempValueList.add(element);
        }
        System.out.println("7: tempValueList");

        List<Float> floatValue = new ArrayList<>();
        for (Double element : originValue) {
          floatValue.add(element.floatValue());
        }
        System.out.println("8: floatValue");

        long[] t_out = new long[originOutput.size()];
        for (int i = 0; i < originOutput.size(); i++) {
          t_out[i] = originOutput.get(i).longValue();
        }
        System.out.println("9: t_out");

        LinearInterpolator li = new LinearInterpolator();
        PolynomialSplineFunction psf =
                li.interpolate(longTinList.stream().mapToDouble(Long::doubleValue).toArray(),
                        floatValue.stream().mapToDouble(Float::doubleValue).toArray());
        System.out.println("10: operation");
        result.success(Arrays.stream(t_out).mapToDouble(psf::value).toArray());

      } catch (Error e) {
        System.out.println("e:" + e.getMessage());
        result.error("error", e.getMessage(),null);
      }
    }

  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
