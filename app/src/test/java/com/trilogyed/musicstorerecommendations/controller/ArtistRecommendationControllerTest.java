package com.trilogyed.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.musicstorerecommendations.model.ArtistRecommendation;
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
@WebMvcTest(ArtistRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ArtistRecommendationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistRecommendationController artistRecommendationController;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldReturnNewArtistRecOnPost() throws Exception {
        ArtistRecommendation inArtistRec = new ArtistRecommendation();
        inArtistRec.setArtistId(3);
        inArtistRec.setUserId(1);
        inArtistRec.setLiked(true);

        ArtistRecommendation outArtistRec = new ArtistRecommendation();
        outArtistRec.setId(5);
        outArtistRec.setArtistId(3);
        outArtistRec.setUserId(1);
        outArtistRec.setLiked(true);

        doReturn(outArtistRec).when(artistRecommendationController).createArtistRec(inArtistRec);

        mockMvc.perform(post("/artistRec")
                        .content(mapper.writeValueAsString(inArtistRec))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(outArtistRec)));
    }

    @Test
    public void shouldReturnArtistRecById() throws Exception {
        ArtistRecommendation outArtistRec = new ArtistRecommendation();
        outArtistRec.setId(5);
        outArtistRec.setArtistId(3);
        outArtistRec.setUserId(1);
        outArtistRec.setLiked(true);

        doReturn(outArtistRec).when(artistRecommendationController).getArtistRec(5);

        mockMvc.perform(get("/artistRec/5")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(outArtistRec)));
    }

    @Test
    public void shouldReturn204OnGoodUpdate() throws Exception {
        ArtistRecommendation inArtistRec = new ArtistRecommendation();
        inArtistRec.setId(5);
        inArtistRec.setArtistId(3);
        inArtistRec.setUserId(1);
        inArtistRec.setLiked(false);

        doNothing().when(artistRecommendationController).updateArtistRec(inArtistRec);

        mockMvc.perform(put("/artistRec")
                        .content(mapper.writeValueAsString(inArtistRec))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn404OnBadUpdate() throws Exception {
        ArtistRecommendation inArtistRec = new ArtistRecommendation();
        inArtistRec.setId(6);
        inArtistRec.setArtistId(3);
        inArtistRec.setUserId(1);
        inArtistRec.setLiked(false);

        doThrow(new IllegalArgumentException("Artist rec not found at given id.")).when(artistRecommendationController).updateArtistRec(inArtistRec);

        mockMvc.perform(put("/artistRec")
                        .content(mapper.writeValueAsString(inArtistRec))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteArtistRedAndReturnNoContent() throws Exception {
        doNothing().when(artistRecommendationController).deleteArtistRec(5);

        mockMvc.perform(delete("/artistRec/5")
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnAllArtistRecs() throws Exception {
        List<ArtistRecommendation> artistRecommendationList = new ArrayList<>();

        ArtistRecommendation outArtistRec = new ArtistRecommendation();
        outArtistRec.setId(5);
        outArtistRec.setArtistId(3);
        outArtistRec.setUserId(1);
        outArtistRec.setLiked(true);

        artistRecommendationList.add(outArtistRec);

        ArtistRecommendation outArtistRec1 = new ArtistRecommendation();
        outArtistRec1.setId(6);
        outArtistRec1.setArtistId(7);
        outArtistRec1.setUserId(2);
        outArtistRec1.setLiked(false);

        artistRecommendationList.add(outArtistRec1);

        doReturn(artistRecommendationList).when(artistRecommendationController).getAllArtistRecs();

        mockMvc.perform(get("/artistRec")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(artistRecommendationList)));
    }
}