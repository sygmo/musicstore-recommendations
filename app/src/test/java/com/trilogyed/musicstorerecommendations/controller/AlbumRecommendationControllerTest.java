package com.trilogyed.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.musicstorerecommendations.model.AlbumRecommendation;
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
@WebMvcTest(AlbumRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AlbumRecommendationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumRecommendationController albumRecommendationController;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldReturnNewAlbumRecOnPost() throws Exception {
        AlbumRecommendation inAlbumRec = new AlbumRecommendation();
        inAlbumRec.setAlbumId(3);
        inAlbumRec.setUserId(1);
        inAlbumRec.setLiked(true);

        AlbumRecommendation outAlbumRec = new AlbumRecommendation();
        outAlbumRec.setId(5);
        outAlbumRec.setAlbumId(3);
        outAlbumRec.setUserId(1);
        outAlbumRec.setLiked(true);

        doReturn(outAlbumRec).when(albumRecommendationController).createAlbumRec(inAlbumRec);

        mockMvc.perform(post("/albumRec")
                        .content(mapper.writeValueAsString(inAlbumRec))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(outAlbumRec)));
    }

    @Test
    public void shouldReturnAlbumRecById() throws Exception {
        AlbumRecommendation outAlbumRec = new AlbumRecommendation();
        outAlbumRec.setId(5);
        outAlbumRec.setAlbumId(3);
        outAlbumRec.setUserId(1);
        outAlbumRec.setLiked(true);

        doReturn(outAlbumRec).when(albumRecommendationController).getAlbumRec(5);

        mockMvc.perform(get("/albumRec/5")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(outAlbumRec)));
    }

    @Test
    public void shouldReturn204OnGoodUpdate() throws Exception {
        AlbumRecommendation inAlbumRec = new AlbumRecommendation();
        inAlbumRec.setId(5);
        inAlbumRec.setAlbumId(3);
        inAlbumRec.setUserId(1);
        inAlbumRec.setLiked(false);

        doNothing().when(albumRecommendationController).updateAlbumRec(inAlbumRec);

        mockMvc.perform(put("/albumRec")
                        .content(mapper.writeValueAsString(inAlbumRec))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn404OnBadUpdate() throws Exception {
        AlbumRecommendation inAlbumRec = new AlbumRecommendation();
        inAlbumRec.setId(6);
        inAlbumRec.setAlbumId(3);
        inAlbumRec.setUserId(1);
        inAlbumRec.setLiked(false);

        doThrow(new IllegalArgumentException("Album rec not found at given id.")).when(albumRecommendationController).updateAlbumRec(inAlbumRec);

        mockMvc.perform(put("/albumRec")
                        .content(mapper.writeValueAsString(inAlbumRec))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteAlbumRedAndReturnNoContent() throws Exception {
        doNothing().when(albumRecommendationController).deleteAlbumRec(5);

        mockMvc.perform(delete("/albumRec/5")
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnAllAlbumRecs() throws Exception {
        List<AlbumRecommendation> albumRecommendationList = new ArrayList<>();

        AlbumRecommendation outAlbumRec = new AlbumRecommendation();
        outAlbumRec.setId(5);
        outAlbumRec.setAlbumId(3);
        outAlbumRec.setUserId(1);
        outAlbumRec.setLiked(true);

        albumRecommendationList.add(outAlbumRec);

        AlbumRecommendation outAlbumRec1 = new AlbumRecommendation();
        outAlbumRec1.setId(6);
        outAlbumRec1.setAlbumId(7);
        outAlbumRec1.setUserId(2);
        outAlbumRec1.setLiked(false);

        albumRecommendationList.add(outAlbumRec1);

        doReturn(albumRecommendationList).when(albumRecommendationController).getAllAlbumRecs();

        mockMvc.perform(get("/albumRec")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(albumRecommendationList)));
    }
}