package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog=blogRepository2.findById(blogId).get();
        Image image=new Image(description,dimensions,blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image=imageRepository2.findById(id).get();
        String imageDimensions= image.getDimensions();
        String[] imgDim=imageDimensions.split("X");
        String[] scrDim=screenDimensions.split("X");
        int l=Integer.parseInt(""+scrDim[0]);
        int b=Integer.parseInt(""+scrDim[1]);
        int l1=Integer.parseInt(""+imgDim[0]);
        int b1=Integer.parseInt(""+imgDim[1]);
        int count=(l/l1)*(b/b1);
        return count;
    }
}
