package com.trilogyed.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.musicstorerecommendations.model.LabelRecommendation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LabelRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class LabelRecommendationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LabelRecommendationController labelRecommendationController;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldReturnNewLabelRecOnPost() throws Exception {
        LabelRecommendation inLabelRec = new LabelRecommendation();
        inLabelRec.setLabelId(3);
        inLabelRec.setUserId(1);
        inLabelRec.setLiked(true);

        LabelRecommendation outLabelRec = new LabelRecommendation();
        outLabelRec.setId(5);
        outLabelRec.setLabelId(3);
        outLabelRec.setUserId(1);
        outLabelRec.setLiked(true);

        doReturn(outLabelRec).when(labelRecommendationController).createLabelRec(inLabelRec);

        mockMvc.perform(post("/labelRec")
                        .content(mapper.writeValueAsString(inLabelRec))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(outLabelRec)));
    }

    @Test
    public void shouldReturnLabelRecById() throws Exception {
        LabelRecommendation outLabelRec = new LabelRecommendation();
        outLabelRec.setId(5);
        outLabelRec.setLabelId(3);
        outLabelRec.setUserId(1);
        outLabelRec.setLiked(true);

        doReturn(outLabelRec).when(labelRecommendationController).getLabelRec(5);

        mockMvc.perform(get("/labelRec/5")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(outLabelRec)));
    }

    @Test
    public void shouldReturn204OnGoodUpdate() throws Exception {
        LabelRecommendation inLabelRec = new LabelRecommendation();
        inLabelRec.setId(5);
        inLabelRec.setLabelId(3);
        inLabelRec.setUserId(1);
        inLabelRec.setLiked(false);

        doNothing().when(labelRecommendationController).updateLabelRec(inLabelRec);

        mockMvc.perform(put("/labelRec")
                        .content(mapper.writeValueAsString(inLabelRec))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn404OnBadUpdate() throws Exception {
        LabelRecommendation inLabelRec = new LabelRecommendation();
        inLabelRec.setId(6);
        inLabelRec.setLabelId(3);
        inLabelRec.setUserId(1);
        inLabelRec.setLiked(false);

        doThrow(new IllegalArgumentException("Label rec not found at given id.")).when(labelRecommendationController).updateLabelRec(inLabelRec);

        mockMvc.perform(put("/labelRec")
                        .content(mapper.writeValueAsString(inLabelRec))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteLabelRedAndReturnNoContent() throws Exception {
        doNothing().when(labelRecommendationController).deleteLabelRec(5);

        mockMvc.perform(delete("/labelRec/5")
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnAllLabelRecs() throws Exception {
        List<LabelRecommendation> labelRecommendationList = new ArrayList<>();

        LabelRecommendation outLabelRec = new LabelRecommendation();
        outLabelRec.setId(5);
        outLabelRec.setLabelId(3);
        outLabelRec.setUserId(1);
        outLabelRec.setLiked(true);

        labelRecommendationList.add(outLabelRec);

        LabelRecommendation outLabelRec1 = new LabelRecommendation();
        outLabelRec1.setId(6);
        outLabelRec1.setLabelId(7);
        outLabelRec1.setUserId(2);
        outLabelRec1.setLiked(false);

        labelRecommendationList.add(outLabelRec1);

        doReturn(labelRecommendationList).when(labelRecommendationController).getAllLabelRecs();

        mockMvc.perform(get("/labelRec")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(labelRecommendationList)));
    }
}