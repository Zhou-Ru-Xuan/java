package com.zhouruxuan.util.log;

public class LogUtil {

    /**
     * 递归逆向打印堆栈及cause(即从最底层的异常开始往上打)
     *
     * @param t          原始异常
     * @param causeDepth 需要递归打印的cause的最大深度
     * @param counter    当前打印的cause的深度计数器(这里必须用引用类型，如果用基本数据类型，你对计数器的修改只能对当前栈帧可见，但是这个计数器，又必须在所有栈帧中可见，所以只能用引用类型)
     * @param stackDepth 每一个异常栈的打印深度
     * @param sb         字符串构造器
     */
    public static void recursiveReversePrintStackCause(Throwable t, int causeDepth, ForwardCounter counter, int stackDepth, StringBuilder sb) {
        if (t == null) {
            return;
        }
        if (t.getCause() != null) {
            recursiveReversePrintStackCause(t.getCause(), causeDepth, counter, stackDepth, sb);
        }
        if (counter.i++ < causeDepth) {
            doPrintStack(t, stackDepth, sb);
        }
    }


    public static void doPrintStack(Throwable t, int stackDepth, StringBuilder sb) {
        StackTraceElement[] stackTraceElements = t.getStackTrace();
        if (sb.lastIndexOf("\t") > -1) {
            sb.deleteCharAt(sb.length() - 1);
            sb.append("Caused: ");
        }
        sb.append(t.getClass().getName()).append(": ").append(t.getMessage()).append("\n\t");
        for (int i = 0; i < stackDepth; ++i) {
            if (i >= stackTraceElements.length) {
                break;
            }
            StackTraceElement element = stackTraceElements[i];
            sb.append(reduceClassName(element.getClassName()))
                    .append("#")
                    .append(element.getMethodName())
                    .append(":")
                    .append(element.getLineNumber())
                    .append("\n\t");
        }
    }

    private static String reduceClassName(String className) {
        // 这里是简化类名的逻辑，具体实现根据需求来
        // 例如，我们可以移除包名，只保留类名
        int lastDotIndex = className.lastIndexOf(".");
        if (lastDotIndex != -1) {
            return className.substring(lastDotIndex + 1);
        }
        return className;
    }
}
