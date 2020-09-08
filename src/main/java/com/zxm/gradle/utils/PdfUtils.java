package com.zxm.gradle.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

/**
 * @Author King james
 * @Description TODO
 * @Date 2020/5/21 0021 14:49
 * @Version 1.0
 */
public class PdfUtils {
    public static void main(String[] args) throws Exception
    {
        //创建Document对象
        Document doc=new Document(PageSize.A4,0,0,50,0);

        //获得PdfWriter实例，将文档放到输出流上
        PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Administrator\\Desktop\\登记表.pdf"));

        //字体设置
        /*
         * 由于itext不支持中文，所以需要进行字体的设置，我这里让itext调用windows系统的中文字体，
         * 找到文件后，打开属性，将文件名及所在路径作为字体名即可。
         */
        //创建BaseFont对象，指明字体，编码方式,是否嵌入
        BaseFont bf=BaseFont.createFont("C:\\Windows\\Fonts\\simkai.ttf", BaseFont.IDENTITY_H, false);
        //创建Font对象，将基础字体对象，字体大小，字体风格
        Font font=new Font(bf,13,Font.NORMAL);
        Font font1=new Font(bf,15,Font.BOLD);


        /*
         * 添加7列的表格
         */

        //创建PdfTable对象
        PdfPTable table=new PdfPTable(7);

        //设置各列的列宽
        table.setTotalWidth(new float[]{100,100,100,100,100,100,80});


        //添加表格内容
        table.addCell(getPDFCell("姓名",font));
        table.addCell("");
        table.addCell(getPDFCell("性别",font));
        table.addCell("");
        table.addCell(getPDFCell("出生年月",font));
        table.addCell("");



        //添加图片
        Image img=Image.getInstance("C:\\Users\\Administrator\\Desktop\\lbj.png",true);
        img.scaleAbsolute(57, 75);
        PdfPCell cell=new PdfPCell(img);
        cell.setRowspan(3);
        table.addCell(cell);


        table.addCell(getPDFCell("学历",font));
        table.addCell("");
        table.addCell(getPDFCell("婚否",font));
        table.addCell("");
        table.addCell(getPDFCell("民族",font));
        table.addCell("");


        table.addCell(getPDFCell("专业",font));
        cell=mergeCol("", font, 2);
        table.addCell(cell);

        table.addCell(getPDFCell("毕业学校",font));
        cell=mergeCol("", font, 2);
        table.addCell(cell);

        table.addCell(getPDFCell("健康状况",font));
        cell=mergeCol("", font, 2);
        table.addCell(cell);

        table.addCell(getPDFCell("户籍所在地",font));
        cell=mergeCol("", font, 3);
        table.addCell(cell);

        table.addCell(getPDFCell("政治面貌",font));
        cell=mergeCol("", font, 2);
        table.addCell(cell);
        table.addCell(getPDFCell("身份证号码",font));
        cell=mergeCol("", font, 3);
        table.addCell(cell);

        table.addCell(getPDFCell("参加工作时间",font));
        cell=mergeCol("", font, 2);
        table.addCell(cell);

        table.addCell(getPDFCell("有无住房",font));
        table.addCell("");

        table.addCell(getPDFCell("要求待遇",font));
        table.addCell("");

        table.addCell(getPDFCell("联系电话",font));
        cell=mergeCol("", font, 2);
        table.addCell(cell);

        table.addCell(getPDFCell("电子邮件",font));
        table.addCell("");

        table.addCell(getPDFCell("手机",font));
        table.addCell("");

        table.addCell(getPDFCell("联系地址",font));
        cell=mergeCol("", font, 6);
        table.addCell(cell);

        table.addCell(getPDFCell("现工作所在地",font));
        cell=mergeCol("", font, 6);
        table.addCell(cell);

        table.addCell(getPDFCell("离职原因",font));
        cell=mergeCol("", font, 6);
        table.addCell(cell);

        cell=mergeRow("简历", font, 6);
        table.addCell(cell);

        table.addCell(getPDFCell("起止时间",font));

        cell=mergeCol("学习/工作单位", font, 3);
        table.addCell(cell);

        cell=mergeCol("专业/职位", font, 2);
        table.addCell(cell);

        for(int i=0;i<5;i++)
        {

            table.addCell(getPDFCell(" ",font));

            cell=mergeCol(" ", font, 3);
            table.addCell(cell);

            cell=mergeCol(" ", font, 2);
            table.addCell(cell);

        }


        cell=mergeRow("家庭情况", font, 6);
        table.addCell(cell);

        table.addCell(getPDFCell("姓名",font));
        table.addCell(getPDFCell("关系",font));
        table.addCell(getPDFCell("年龄",font));
        table.addCell(getPDFCell("文化程度",font));
        cell=mergeCol("现工作单位", font, 2);
        table.addCell(cell);

        for(int i=0;i<5;i++)
        {

            table.addCell(getPDFCell(" ",font));
            table.addCell(getPDFCell(" ",font));
            table.addCell(getPDFCell(" ",font));
            table.addCell(getPDFCell(" ",font));
            cell=mergeCol(" ", font, 2);
            table.addCell(cell);

        }


        table.addCell(getPDFCell("特别提示",font));

        cell=mergeCol("1. 本人承诺保证所填写资料真实。 \n2. 保证遵守公司招聘有关规程和国家有关法规。  \n3. 请填写好招聘登记表，带齐照片、学历、职称证书的有效证件及相关复印件。\n", font, 6);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);



        //文档写入内容

        doc.open();
        Paragraph title=new Paragraph("招聘人员登记表\n\n\n",font1);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(title);
        doc.add(table);
        doc.close();



    }

    //合并行的静态函数
    public static PdfPCell mergeRow(String str,Font font,int i) {

        //创建单元格对象，将内容及字体传入
        PdfPCell cell=new PdfPCell(new Paragraph(str,font));
        //设置单元格内容居中
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        //将该单元格所在列包括该单元格在内的i行单元格合并为一个单元格
        cell.setRowspan(i);

        return cell;
    }

    //合并列的静态函数
    public static PdfPCell mergeCol(String str,Font font,int i) {

        PdfPCell cell=new PdfPCell(new Paragraph(str,font));
        cell.setMinimumHeight(25);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        //将该单元格所在行包括该单元格在内的i列单元格合并为一个单元格
        cell.setColspan(i);

        return cell;
    }

    //获取指定内容与字体的单元格
    public static PdfPCell getPDFCell(String string, Font font)
    {
        //创建单元格对象，将内容与字体放入段落中作为单元格内容
        PdfPCell cell=new PdfPCell(new Paragraph(string,font));

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        //设置最小单元格高度
        cell.setMinimumHeight(25);
        return cell;
    }
}
