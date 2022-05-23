package com.ct.test.kettle;

import java.util.Map;
import java.util.UUID;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KettleDemo {


    protected static final Logger logger_info = LoggerFactory.getLogger(KettleDemo.class);


    public static void main(String[] args) throws KettleException {
//        runKettleTransfer(null, "C:\\Users\\GaoHao\\Desktop\\Text\\ct_test.ktr");

        String fileName = "C:\\Users\\GaoHao\\Desktop\\Text\\ct_test.ktr";
        System.out.println("-----------");
        KettleEnvironment.init();
        TransMeta transMeta = new TransMeta(fileName);
        Trans trans = new Trans(transMeta);
        trans.execute(null);
        trans.waitUntilFinished();

    }

    /**
     * 执行作业
     *
     * @param initKettleParam
     * @param kjbFilePath
     * @return
     */
    public static boolean runKettleJob(Map<String, String> initKettleParam, String kjbFilePath) {
        String uuid = UUID.randomUUID().toString();
        logger_info.info("ExecKettleUtil@runKettleJob:" + uuid + " {kjbFilePath:" + kjbFilePath + "}");
        try {
            KettleEnvironment.init();
            // 初始化job路径
            JobMeta jobMeta = new JobMeta(kjbFilePath, null);
            Job job = new Job(null, jobMeta);
            // 初始化job参数，脚本中获取参数值：${variableName}
            if (initKettleParam != null) {
                for (String variableName : initKettleParam.keySet()) {
                    job.setVariable(variableName, initKettleParam.get(variableName));
                }
            }

            job.start();
            job.waitUntilFinished();
            if (job.getErrors() > 0) {
                logger_info.info("ExecKettleUtil@runKettleJob:" + uuid + " 执行失败");
            } else {
                logger_info.info("ExecKettleUtil@runKettleJob:" + uuid + " 执行成功");
            }
            return true;
        } catch (Exception e) {
            logger_info.error("ExecKettleUtil@runKettleJob:" + uuid, e);
            return false;
        }
    }

    /**
     * 执行转换
     *
     * @param initKettleParam
     * @param ktrFilePath
     * @return
     */
    public static boolean runKettleTransfer(Map<String, String> initKettleParam, String ktrFilePath) {
        Trans trans = null;
        String uuid = UUID.randomUUID().toString();
        logger_info.info("ExecKettleUtil@runKettleTransfer:" + uuid + " {ktrFilePath:" + ktrFilePath + "}");
        try {
            // 初始化
            KettleEnvironment.init();
            EnvUtil.environmentInit();
            TransMeta transMeta = new TransMeta(ktrFilePath);
            // 转换
            trans = new Trans(transMeta);
            // 初始化trans参数，脚本中获取参数值：${variableName}
            if (initKettleParam != null) {
                for (String variableName : initKettleParam.keySet()) {
                    trans.setVariable(variableName, initKettleParam.get(variableName));
                }
            }

            // 执行转换
            trans.execute(null);
            // 等待转换执行结束
            trans.waitUntilFinished();
            if (trans.getErrors() > 0) {
                logger_info.info("ExecKettleUtil@runKettleTransfer:" + uuid + " 执行失败");
            } else {
                logger_info.info("ExecKettleUtil@runKettleTransfer:" + uuid + " 执行成功");
            }
            return true;
        } catch (Exception e) {
            logger_info.error("ExecKettleUtil@runKettleTransfer:" + uuid, e);
            return false;
        }
    }


}
