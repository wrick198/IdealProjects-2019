package com.knowledge_network.circle.controller;

import com.knowledge_network.circle.entity.Section;
import com.knowledge_network.circle.service.SectionService;
import com.knowledge_network.circle.vo.SectionVO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.IOUtils;
import com.knowledge_network.support.utils.JsonMapper;
import com.knowledge_network.user.vo.ListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wshish000 on 18-3-8
 */
@RestController
@RequestMapping(value = "/circle/section/")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseResult<ListVO<SectionVO>> listSection(){

        List<Section> sections = sectionService.findAll();
        long totalCount = sections.size();
        List<SectionVO> sectionVOS = new ArrayList<>();

        if(sections != null){
            for(Section section: sections){
                sectionVOS.add(new SectionVO(section));
            }
        }

        //TODO 此处没有做分页
        return ResponseResult.newSucceedInstance(null, new ListVO<SectionVO>(totalCount, 0, (int)totalCount, sectionVOS));
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public ResponseResult<String> createSection(HttpServletRequest request) {

        String json = IOUtils.readDataFromHttpServletRequest(request);

        Asserts.hasText(json, ResponseErrorEnum.BAD_REQUEST);

        Map<String, Object> datas = JsonMapper.json2Map(json);

        Section section = new Section();
        section.setName((String)datas.get("name"));
        sectionService.save(section);

        return ResponseResult.newSucceedInstance("Create Section Successfully", null);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public ResponseResult<String> editSection(@PathVariable String id, HttpServletRequest request) {

        String json = IOUtils.readDataFromHttpServletRequest(request);

        Asserts.hasText(json, ResponseErrorEnum.BAD_REQUEST);

        Map<String, Object> datas = JsonMapper.json2Map(json);

        Section section = sectionService.findById(Integer.parseInt(id));
        section.setName((String)datas.get("name"));
        sectionService.save(section);

        return ResponseResult.newSucceedInstance("Edit Section Successfully", null);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseResult<String> deleteSection(@PathVariable String id) {

        sectionService.delete(Integer.parseInt(id));

        return ResponseResult.newSucceedInstance("Delete Section Successfully", null);
    }
}
