package com.dmz.basic.implement;

import com.dmz.basic.document.UserDocument;
import com.dmz.basic.idao.IUserDocumentDao;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author dmz
 * @date 2017/8/17
 */
@Repository
public class UserDocumentDao implements IUserDocumentDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public UserDocument findByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        UserDocument user = mongoTemplate.findOne(query, UserDocument.class);
        return user;
    }

    @Override
    public void saveUser(UserDocument userDocument) {
        mongoTemplate.save(userDocument);
    }


    public void saveFile(String collectionName, File file, String fileid, String companyid, String filename) {
        try {
            DB db = mongoTemplate.getDb();
            // 存储fs的根节点
            GridFS gridFS = new GridFS(db, collectionName);
            GridFSInputFile gfs = gridFS.createFile(file);
            gfs.put("aliases", companyid);
            gfs.put("filename", fileid);
            gfs.put("contentType", filename.substring(filename.lastIndexOf(".")));
            gfs.save();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("存储文件时发生错误！！！");
        }
    }


    public GridFSDBFile retrieveFileOne(String collectionName, String fileId) {
        try {
            DB db = mongoTemplate.getDb();
            // 获取fs的根节点
            GridFS gridFS = new GridFS(db, collectionName);
            GridFSDBFile dbfile = gridFS.findOne(fileId);
            if (dbfile != null) {
                return dbfile;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}
