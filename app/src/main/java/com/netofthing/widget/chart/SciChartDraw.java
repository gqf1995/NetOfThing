package com.netofthing.widget.chart;

/**
 * Created by 郭青枫 on 2018/1/11 0011.
 */

//public class SciChartDraw {
//    //X轴标签的类
//    protected XAxis xAxisKline, xAxisVolume;
//    //Y轴左侧的线
//    protected YAxis axisLeftKline, axisLeftVolume;
//    //Y轴右侧的线
//    protected YAxis axisRightKline, axisRightVolume;
//
//    //解析数据
//    private DataParse mData;
//    //K线图数据
//    private ArrayList<KLineBean> kLineDatas;
//
//    public SciChartSurface mChartKline;
//    public SciChartSurface mChartVolume;
//
//    Context mContext;
//
//    OnClick onClick;
//    private final SciChartVerticalGroup verticalGroup = new SciChartVerticalGroup();
//
//    private final DoubleRange sharedXRange = new DoubleRange();
//
//    private static final String VOLUME = "Volume";
//    private static final String PRICES = "Prices";
//    private static final String RSI = "RSI";
//    private static final String MACD = "MACD";
//
//    protected final SciChartBuilder sciChartBuilder = SciChartBuilder.instance();
//
//    public DataParse getmData() {
//        return mData;
//    }
//
//    public interface OnClick {
//        void click(int xPosition);
//    }
//
//    public void setOnClick(OnClick onClick) {
//        this.onClick = onClick;
//    }
//
//    public void setData(Context context, DataParse data, SciChartSurface chartKline, SciChartSurface chartVolume) {
//        Log.i("KlineDraw", "setData");
//
//        mData = data;
//        mContext = context;
//        mChartKline = chartKline;
//        mChartVolume = chartVolume;
//
//        final PriceSeries priceData = data.getKLine();
//
//        final PricePaneModel pricePaneModel = new PricePaneModel(sciChartBuilder, priceData);
//        final VolumePaneModel volumePaneModel = new VolumePaneModel(sciChartBuilder, priceData);
//        initKlineChart(chartKline, pricePaneModel, true);
//        initVolumeChart(chartVolume, volumePaneModel, false);
//    }
//
//    private void initVolumeChart(SciChartSurface surface, BasePaneModel model, boolean isMainPane) {
//        final CategoryDateAxis xAxis = sciChartBuilder.newCategoryDateAxis()
//                .withVisibility(isMainPane ? View.VISIBLE : View.GONE)
//                .withVisibleRange(sharedXRange)
//                .withGrowBy(0, 0.05)
//                .build();
//
//        surface.getXAxes().add(xAxis);
//        surface.getYAxes().add(model.yAxis);
//        surface.getRenderableSeries().addAll(model.renderableSeries);
//
//        surface.getChartModifiers().add(sciChartBuilder
//                .newModifierGroup()
//                .withXAxisDragModifier()
//                .withReceiveHandledEvents(true)
//                .withDragMode(AxisDragModifierBase.AxisDragMode.Pan)
//                .withClipModex(ClipMode.StretchAtExtents).build()
//                .withPinchZoomModifier()
//                .withReceiveHandledEvents(true)
//                .withXyDirection(Direction2D.XDirection).build()
//                .withZoomPanModifier()
//                .withReceiveHandledEvents(true).build()
//                .withZoomExtentsModifier()
//                .withReceiveHandledEvents(true).build()
//                .withLegendModifier()
//                .withShowCheckBoxes(false)
//                .build()
//                .build());
//
//        surface.setAnnotations(model.annotations);
//
//        verticalGroup.addSurfaceToGroup(surface);
//    }
//
//    private void initKlineChart(SciChartSurface surface, BasePaneModel model, boolean isMainPane) {
//        final CategoryDateAxis xAxis = sciChartBuilder.newCategoryDateAxis()
//                .withVisibility(isMainPane ? View.VISIBLE : View.GONE)
//                .withVisibleRange(sharedXRange)
//                .withGrowBy(0, 0.05)
//                .build();
//
//        surface.getXAxes().add(xAxis);
//        surface.getYAxes().add(model.yAxis);
//
//        surface.getRenderableSeries().addAll(model.renderableSeries);
//
//        surface.getChartModifiers().add(sciChartBuilder
//                .newModifierGroup()
//                .withXAxisDragModifier().withReceiveHandledEvents(true).withDragMode(AxisDragModifierBase.AxisDragMode.Pan).withClipModex(ClipMode.StretchAtExtents).build()
//                .withPinchZoomModifier().withReceiveHandledEvents(true).withXyDirection(Direction2D.XDirection).build()
//                .withZoomPanModifier().withReceiveHandledEvents(true).build()
//                .withZoomExtentsModifier().withReceiveHandledEvents(true).build()
//                .withLegendModifier().withShowCheckBoxes(false).build()
//                .build());
//
//        surface.setAnnotations(model.annotations);
//
//        verticalGroup.addSurfaceToGroup(surface);
//    }
//
//    private abstract static class BasePaneModel {
//        public final RenderableSeriesCollection renderableSeries;
//        public final AnnotationCollection annotations;
//        public final NumericAxis yAxis;
//        public final String title;
//
//        protected BasePaneModel(SciChartBuilder builder, String title, String yAxisTextFormatting, boolean isFirstPane) {
//            this.title = title;
//            this.renderableSeries = new RenderableSeriesCollection();
//            this.annotations = new AnnotationCollection();
//
//            this.yAxis = builder.newNumericAxis()
//                    .withAxisId(title)
//                    .withTextFormatting(yAxisTextFormatting)
//                    .withAutoRangeMode(AutoRange.Always)
//                    .withDrawMinorGridLines(false)
//                    .withDrawMajorGridLines(false)
//                    .withMinorsPerMajor(isFirstPane ? 4 : 2)
//                    .withMaxAutoTicks(isFirstPane ? 8 : 4)
//                    .withGrowBy(isFirstPane ? new DoubleRange(0.05d, 0.05d) : new DoubleRange(0d, 0d))
//                    .build();
//        }
//
//        final void addRenderableSeries(BaseRenderableSeries renderableSeries) {
//            renderableSeries.setClipToBounds(true);
//            this.renderableSeries.add(renderableSeries);
//        }
//    }
//
//    private static class PricePaneModel extends BasePaneModel {
//
//        public PricePaneModel(SciChartBuilder builder, PriceSeries prices) {
//            super(builder, PRICES, "$0.0", true);
//
//            // Add the main OHLC chart
//            final OhlcDataSeries<Date, Double> stockPrices = builder.newOhlcDataSeries(Date.class, Double.class).build();
//            stockPrices.append(prices.getDateData(), prices.getOpenData(), prices.getHighData(), prices.getLowData(), prices.getCloseData());
//            addRenderableSeries(builder.newCandlestickSeries().withDataSeries(stockPrices).withFillDownColor(CommonUtils.getColor(UserSet.getinstance().getDropColor())).withFillUpColor(CommonUtils.getColor(UserSet.getinstance().getRiseColor())).withYAxisId(PRICES).build());
//
//            //            final XyDataSeries<Date, Double> maLow = builder.newXyDataSeries(Date.class, Double.class).withSeriesName("Low Line").build();
//            //            maLow.append(prices.getDateData(), MovingAverage.movingAverage(prices.getCloseData(), 50));
//            //            addRenderableSeries(builder.newLineSeries().withDataSeries(maLow).withStrokeStyle(CommonUtils.getColor(UserSet.getinstance().getDropColor()), 1f).withYAxisId(PRICES).build());
//
//            //            final XyDataSeries<Date, Double> maHigh = builder.newXyDataSeries(Date.class, Double.class).withSeriesName("High Line").build();
//            //            maHigh.append(prices.getDateData(), MovingAverage.movingAverage(prices.getCloseData(), 200));
//            //            addRenderableSeries(builder.newLineSeries().withDataSeries(maHigh).withStrokeStyle(CommonUtils.getColor(UserSet.getinstance().getRiseColor()), 1f).withYAxisId(PRICES).build());
//
//            Collections.addAll(annotations,
//                    builder.newAxisMarkerAnnotation().withY1(stockPrices.getYValues().get(stockPrices.getCount() - 1)).withBackgroundColor(CommonUtils.getColor(UserSet.getinstance().getDropColor())).withYAxisId(PRICES).build()
//                    //,
//                    //builder.newAxisMarkerAnnotation().withY1(maLow.getYValues().get(maLow.getCount() - 1)).withBackgroundColor(CommonUtils.getColor(UserSet.getinstance().getDropColor())).withYAxisId(PRICES).build(),
//                    //builder.newAxisMarkerAnnotation().withY1(maHigh.getYValues().get(maHigh.getCount() - 1)).withBackgroundColor(CommonUtils.getColor(UserSet.getinstance().getRiseColor())).withYAxisId(PRICES).build()
//            );
//        }
//    }
//
//    private static class VolumePaneModel extends BasePaneModel {
//        public VolumePaneModel(SciChartBuilder builder, PriceSeries prices) {
//            super(builder, VOLUME, "$0", false);
//
//            final XyDataSeries<Date, Double> volumePrices = builder.newXyDataSeries(Date.class, Double.class).build();
//            volumePrices.append(prices.getDateData(), ListUtil.select(prices.getVolumeData(), new Func1<Long, Double>() {
//                @Override
//                public Double func(Long arg) {
//                    return arg.doubleValue();
//                }
//            }));
//            addRenderableSeries(builder.newColumnSeries().withDataSeries(volumePrices).withLinearGradientColors(CommonUtils.getColor(UserSet.getinstance().getDropColor()), CommonUtils.getColor(UserSet.getinstance().getRiseColor())).withYAxisId(VOLUME).build());
//
//            Collections.addAll(annotations,
//                    builder.newAxisMarkerAnnotation().withY1(volumePrices.getYValues().get(volumePrices.getCount() - 1)).withYAxisId(VOLUME).build());
//        }
//    }
//
//    private static class RsiPaneModel extends BasePaneModel {
//        public RsiPaneModel(SciChartBuilder builder, PriceSeries prices) {
//            super(builder, RSI, "0.0", false);
//
//            final XyDataSeries<Date, Double> rsiSeries = builder.newXyDataSeries(Date.class, Double.class).withSeriesName("RSI").build();
//            rsiSeries.append(prices.getDateData(), MovingAverage.rsi(prices, 14));
//            addRenderableSeries(builder.newLineSeries().withDataSeries(rsiSeries).withStrokeStyle(0xFFC6E6FF, 1f).withYAxisId(RSI).build());
//
//            Collections.addAll(annotations,
//                    builder.newAxisMarkerAnnotation().withY1(rsiSeries.getYValues().get(rsiSeries.getCount() - 1)).withYAxisId(RSI).build());
//        }
//    }
//
//    private static class MacdPaneModel extends BasePaneModel {
//        public MacdPaneModel(SciChartBuilder builder, PriceSeries prices) {
//            super(builder, MACD, "0.00", false);
//
//            final MovingAverage.MacdPoints macdPoints = MovingAverage.macd(prices.getCloseData(), 12, 25, 9);
//
//            final XyDataSeries<Date, Double> histogramDataSeries = builder.newXyDataSeries(Date.class, Double.class).withSeriesName("Histogram").build();
//            histogramDataSeries.append(prices.getDateData(), macdPoints.divergenceValues);
//            addRenderableSeries(builder.newColumnSeries().withDataSeries(histogramDataSeries).withYAxisId(MACD).build());
//
//            final XyyDataSeries<Date, Double> macdDataSeries = builder.newXyyDataSeries(Date.class, Double.class).withSeriesName("MACD").build();
//            macdDataSeries.append(prices.getDateData(), macdPoints.macdValues, macdPoints.signalValues);
//            addRenderableSeries(builder.newBandSeries().withDataSeries(macdDataSeries).withYAxisId(MACD).build());
//
//            Collections.addAll(annotations,
//                    builder.newAxisMarkerAnnotation().withY1(histogramDataSeries.getYValues().get(histogramDataSeries.getCount() - 1)).withYAxisId(MACD).build(),
//                    builder.newAxisMarkerAnnotation().withY1(macdDataSeries.getYValues().get(macdDataSeries.getCount() - 1)).withYAxisId(MACD).build());
//        }
//    }
//}
