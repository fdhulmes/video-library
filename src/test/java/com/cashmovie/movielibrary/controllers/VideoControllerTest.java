package com.cashmovie.movielibrary.controllers;

import com.cashmovie.movielibrary.entities.Video;
import com.cashmovie.movielibrary.services.VideoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith (SpringRunner.class)
public class VideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoService videoService;

    @Test
    public void testPostVideo() throws Exception {
        Video testVideo = new Video("Video", "/videos/1.mp4", "Frank", "Test");
        String expectedVideo = "{\"id\":null,\"title\":\"Video\",\"filePath\":\"/videos/1.mp4\"," +
                "\"author\":\"Frank\",\"date\":null,\"description\":\"Test\"}";
        when(videoService.postNewVideo(testVideo)).thenReturn(testVideo);
        this.mockMvc.perform(post("/api/videos/upload")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedVideo))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetVideos() throws Exception {
        Video testVideo = new Video("Video", "/videos/1.mp4", "Frank", "Test");
        List<Video> testList = Arrays.asList(testVideo);
        String expectedVideo = "[{\"id\":null,\"title\":\"Video\",\"filePath\":\"/videos/1.mp4\"," +
                "\"author\":\"Frank\",\"date\":null,\"description\":\"Test\"}]";
        when(videoService.getRecent()).thenReturn(testList);
        this.mockMvc.perform(get("/api/videos/home").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedVideo))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetSingleVideo() throws Exception {
        Video testVideo = new Video("Video", "/videos/1.mp4", "Frank", "Test");
        String expectedVideo = "{\"id\":null,\"title\":\"Video\",\"filePath\":\"/videos/1.mp4\"," +
                "\"author\":\"Frank\",\"date\":null,\"description\":\"Test\"}";
        when(videoService.getVideoById(1L)).thenReturn(testVideo);
        this.mockMvc.perform(get("/api/videos/id=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedVideo))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteVideo() throws Exception {
        Video testVideo = new Video("Video", "/videos/1.mp4", "Frank", "Test");
        String expectedVideo = "{\"id\":null,\"title\":\"Video\",\"filePath\":\"/videos/1.mp4\"," +
                "\"author\":\"Frank\",\"date\":null,\"description\":\"Test\"}";
        when(videoService.deleteVideoById(1L)).thenReturn(testVideo);
        this.mockMvc.perform(delete("/api/videos/id=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedVideo))
                .andExpect(status().isOk());
    }

  @Test
  public void testGetVideoByAuthor() throws Exception {
    Video testVideo1 = new Video("Video1", "/videos/1.mp4", "Frank", "Test");
    Video testVideo2 = new Video("Video2", "/videos/2.mp4", "Frank", "Test");
    List<Video> testList = Arrays.asList(testVideo1,testVideo2);
    String expectedVideos = "[" +
                             "{\"id\":null,\"title\":\"Video1\",\"filePath\":\"/videos/1.mp4\"," +
                             "\"author\":\"Frank\",\"date\":null,\"description\":\"Test\"}," +
                             "{\"id\":null,\"title\":\"Video2\",\"filePath\":\"/videos/2.mp4\"," +
                             "\"author\":\"Frank\",\"date\":null,\"description\":\"Test\"}" +
                             "]";
    when(videoService.getVideosByAuthor("Frank")).thenReturn(testList);
    this.mockMvc.perform(get("/api/videos/user=Frank").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedVideos))
                .andExpect(status().isOk());
  }

  // Unused for now
    /*
    private String buildListJson(List<Video> list) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int ind = 0;
        for(Video v : list){
            if(ind != 0){
                sb.append(",");
            }
            sb.append(objectMapper.writeValueAsString(v));
            ind++;
        }
        sb.append("]");
        return sb.toString();
    }
    */


}
