package relative.basic.properties.util;

/**
 * 该工具处理方式源于网上
 * 相关地址为：https://juejin.cn/post/6862250404227973133
 *
 *
 * @Author chenSy
 * @Date 2022/12/07 16:56
 * @Description
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 扩展java.util.Properties工具类：（代码过于复杂，不要轻易引用，可以新增加一个属性文件，避免对原来的文件有影响）
 * 1. 保存原配置文件中的注释
 * 2. 保存原配置文件中的配置项顺序
 */
@Slf4j
public class OrderedProperties {

//    // 原始属性键值对(无序)
//    private final Properties props;
//    // 保存key与comment的映射，同时利用这个映射来保证key的顺序。
//    private final LinkedHashMap<String, String> keyCommentMap = new LinkedHashMap<String, String>();
//
//    public OrderedProperties() {
//        super();
//        props = new Properties();
//    }
//
//    /**
//     * 设置属性，如果key已经存在，那么将其对应value值覆盖。
//     *
//     * @param key     键
//     * @param value   与键对应的值
//     * @param comment 对键值对的说明
//     * @return
//     */
//    public synchronized String setProperty(String key, String value, String comment) {
//        Object oldValue = props.setProperty(key, value);
//        if (StringUtils.isEmpty(comment)) {
//            if (!keyCommentMap.containsKey(key)) {
//                keyCommentMap.put(key, comment);
//            }
//        } else {
//            keyCommentMap.put(key, comment);
//        }
//        return (String) oldValue;
//    }
//
//    /**
//     * 根据key获取属性表中相应的value。
//     *
//     * @param key
//     * @return
//     */
//    public String getProperty(String key) {
//        return props.getProperty(key);
//    }
//
//    /**
//     * 从一个字符流中读取属性到属性表中
//     *
//     * @param reader
//     * @throws IOException
//     */
//    public synchronized void load(Reader reader) throws IOException {
//        load0(new LineReader(reader));
//    }
//
//    /**
//     * 将属性表中的属性写到字节流里面。
//     *
//     * @param out
//     * @throws IOException
//     */
//    public void store(OutputStream out) throws IOException {
//        store0(new BufferedWriter(new OutputStreamWriter(out, "utf-8")), true);
//    }
//
//    /**
//     * 获取属性表中所有key的集合
//     */
//    public Set<String> propertyNames() {
//        return props.stringPropertyNames();
//    }
//
//
//    /**
//     * 获取property封装到类属性props和keyCommentMap
//     * @param lr
//     * @throws IOException
//     */
//    private void load0(LineReader lr) throws IOException {
//        int limit;
//        int keyLen;
//        int valueStart;
//        char c;
//        boolean hasSep;
//        boolean precedingBackslash;
//        StringBuffer buffer = new StringBuffer();
//        StringBuilder outBuffer = new StringBuilder();
//
//        while ((limit = lr.readLine()) >= 0) {
//            keyLen = 0;
//            valueStart = limit;
//            hasSep = false;
//            //获取注释
//            c = lr.lineBuf[keyLen];
//            if(c == '#' || c == '!'){
//                String comment = loadConvert(lr.lineBuf, 1, limit - 1, outBuffer);
//                if(buffer.length() > 0){
//                    buffer.append("\n");
//                }
//                buffer.append(comment);
//                continue;
//            }
//            precedingBackslash = false;
//            // load property
//            setProperty(key, value, buffer.toString());
//            // reset buffer
//            buffer = new StringBuffer();
//        }
//    }
//
//    private String loadConvert (char[] in, int off, int len, char[] convtBuf) {
//        if (convtBuf.length < len) {
//            int newLen = len * 2;
//            if (newLen < 0) {
//                newLen = Integer.MAX_VALUE;
//            }
//            convtBuf = new char[newLen];
//        }
//        char aChar;
//        char[] out = convtBuf;
//        int outLen = 0;
//        int end = off + len;
//
//        while (off < end) {
//            aChar = in[off++];
//            if (aChar == '\\') {
//                aChar = in[off++];
//                if(aChar == 'u') {
//                    // Read the xxxx
//                    int value=0;
//                    for (int i=0; i<4; i++) {
//                        aChar = in[off++];
//                        switch (aChar) {
//                            case '0': case '1': case '2': case '3': case '4':
//                            case '5': case '6': case '7': case '8': case '9':
//                                value = (value << 4) + aChar - '0';
//                                break;
//                            case 'a': case 'b': case 'c':
//                            case 'd': case 'e': case 'f':
//                                value = (value << 4) + 10 + aChar - 'a';
//                                break;
//                            case 'A': case 'B': case 'C':
//                            case 'D': case 'E': case 'F':
//                                value = (value << 4) + 10 + aChar - 'A';
//                                break;
//                            default:
//                                throw new IllegalArgumentException(
//                                        "Malformed \\uxxxx encoding.");
//                        }
//                    }
//                    out[outLen++] = (char)value;
//                } else {
//                    if (aChar == 't') aChar = '\t';
//                    else if (aChar == 'r') aChar = '\r';
//                    else if (aChar == 'n') aChar = '\n';
//                    else if (aChar == 'f') aChar = '\f';
//                    out[outLen++] = aChar;
//                }
//            } else {
//                out[outLen++] = aChar;
//            }
//        }
//        return new String (out, 0, outLen);
//    }
//
//
//    /**
//     * 基于java.util.Properties.LineReader进行改造, 删除过滤comment相关代码
//     */
//    class LineReader {
//        public LineReader(InputStream inStream) {
//            this.inStream = inStream;
//            inByteBuf = new byte[8192];
//        }
//
//        public LineReader(Reader reader) {
//            this.reader = reader;
//            inCharBuf = new char[8192];
//        }
//
//        byte[] inByteBuf;
//        char[] inCharBuf;
//        char[] lineBuf = new char[1024];
//        int inLimit = 0;
//        int inOff = 0;
//        InputStream inStream;
//        Reader reader;
//
//
//        int readLine() throws IOException {
//            int len = 0;
//            char c = 0;
//
//            boolean skipWhiteSpace = true;
//            boolean isNewLine = true;
//            boolean appendedLineBegin = false;
//            boolean precedingBackslash = false;
//            boolean skipLF = false;
//
//            while (true) {
//                if (inOff >= inLimit) {
//                    inLimit = (inStream==null)?reader.read(inCharBuf)
//                            :inStream.read(inByteBuf);
//                    inOff = 0;
//                    if (inLimit <= 0) {
//                        if (len == 0) {
//                            return -1;
//                        }
//                        return len;
//                    }
//                }
//                if (inStream != null) {
//                    // The line below is equivalent to calling a
//                    // ISO8859-1 decoder.
//                    c = (char) (0xff & inByteBuf[inOff++]);
//                } else {
//                    c = inCharBuf[inOff++];
//                }
//                if (skipLF) {
//                    skipLF = false;
//                    if (c == '\n') {
//                        continue;
//                    }
//                }
//                if (skipWhiteSpace) {
//                    if (c == ' ' || c == '\t' || c == '\f') {
//                        continue;
//                    }
//                    if (!appendedLineBegin && (c == '\r' || c == '\n')) {
//                        continue;
//                    }
//                    skipWhiteSpace = false;
//                    appendedLineBegin = false;
//                }
//                if (isNewLine) {
//                    isNewLine = false;
//                }
//
//                if (c != '\n' && c != '\r') {
//                    lineBuf[len++] = c;
//                    if (len == lineBuf.length) {
//                        int newLength = lineBuf.length * 2;
//                        if (newLength < 0) {
//                            newLength = Integer.MAX_VALUE;
//                        }
//                        char[] buf = new char[newLength];
//                        System.arraycopy(lineBuf, 0, buf, 0, lineBuf.length);
//                        lineBuf = buf;
//                    }
//                    // flip the preceding backslash flag
//                    if (c == '\\') {
//                        precedingBackslash = !precedingBackslash;
//                    } else {
//                        precedingBackslash = false;
//                    }
//                }
//                else {
//                    // reached EOL
//                    if (len == 0) {
//                        isNewLine = true;
//                        skipWhiteSpace = true;
//                        len = 0;
//                        continue;
//                    }
//                    if (inOff >= inLimit) {
//                        inLimit = (inStream==null)
//                                ?reader.read(inCharBuf)
//                                :inStream.read(inByteBuf);
//                        inOff = 0;
//                        if (inLimit <= 0) {
//                            return len;
//                        }
//                    }
//                    if (precedingBackslash) {
//                        len -= 1;
//                        //skip the leading whitespace characters in following line
//                        skipWhiteSpace = true;
//                        appendedLineBegin = true;
//                        precedingBackslash = false;
//                        if (c == '\r') {
//                            skipLF = true;
//                        }
//                    } else {
//                        return len;
//                    }
//                }
//            }
//        }
//        }
//
//    /**
//     * 更新property文件，基于keyCommentMap就行循环，确保顺序
//     * @param bw
//     * @param escUnicode
//     * @throws IOException
//     */
//    private void store0(BufferedWriter bw, boolean escUnicode)
//            throws IOException{
//        synchronized (this) {
//            Iterator<Map.Entry<String, String>> kvIter = keyCommentMap.entrySet().iterator();
//            while(kvIter.hasNext()){
//                Map.Entry<String, String> entry = kvIter.next();
//                String key = entry.getKey();
//                String val = getProperty(key);
//                String comment = entry.getValue();
//                key = saveConvert(key, true, escUnicode);
//                /* No need to escape embedded and trailing spaces for value, hence
//                 * pass false to flag.
//                 */
//                val = saveConvert(val, false, escUnicode);
//                if(StringUtils.isNotEmpty(comment)) {
//                    writeComments(bw, comment);
//                }
//                if(StringUtils.isNotEmpty(val)) {
//                    bw.write(key + "=" + val);
//                } else {
//                    bw.write(key);
//                }
//                bw.newLine();
//            }
//        }
//        bw.flush();
//    }
// }

}

