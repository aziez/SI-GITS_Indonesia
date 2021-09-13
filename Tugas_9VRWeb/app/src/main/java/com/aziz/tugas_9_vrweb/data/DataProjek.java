package com.aziz.tugas_9_vrweb.data;

import java.util.ArrayList;

public class DataProjek {
    private static String[] projekName = {
            "Commercial and Public",
            "Hotel And Villa",
            "Housing And Apartement",
            "Interactive 3D",
            "Interior",
            "Animation",
            "Videography",
            "VR 360",
            "Masterplan"
    };

    private static String url[] = {
            "https://www.lokcay.com/portfolio/commercial/",
            "https://www.lokcay.com/portfolio/resort/",
            "https://www.lokcay.com/portfolio/residential/",
            "https://www.lokcay.com/portfolio/interactive-3d/",
            "https://www.lokcay.com/portfolio/interior/",
            "https://www.lokcay.com/portfolio/lokcay-animation/",
            "https://www.lokcay.com/portfolio/videography/",
            "https://www.lokcay.com/portfolio/vr360/",
            "https://www.lokcay.com/portfolio/lake-view-residence/",
    };

    private static String hero[] = {
           "https://www.lokcay.com/wp-content/uploads/2014/12/hero_commercial-e1579173834967.jpg",
            "https://www.lokcay.com/wp-content/uploads/2017/05/hero_resort-e1579173799723.jpg",
            "https://www.lokcay.com/wp-content/uploads/2014/12/hero_residential-e1579173887196.jpg",
            "https://www.lokcay.com/wp-content/uploads/2020/01/interaktif-1.jpg",
            "https://www.lokcay.com/wp-content/uploads/2014/12/hero_interior-e1496729990436.jpg",
            "https://www.lokcay.com/wp-content/uploads/2020/01/vg.jpg",
            "https://www.lokcay.com/wp-content/uploads/2014/12/hero_landscape-e1579174186161.jpg",
            "https://www.lokcay.com/wp-content/uploads/2017/05/rumah-video-e1579174157675.jpg",
            "https://www.lokcay.com/wp-content/uploads/2018/07/hero_vr360.jpg",


    };

    public static ArrayList<Projek>  getDataProjek() {
        ArrayList<Projek> list = new ArrayList<>();

        for (int i = 0; i < projekName.length; i++){
            Projek projek = new Projek();

            projek.setNama(projekName[i]);
            projek.setHero(hero[i]);
            projek.setUrl(url[i]);

            list.add(projek);
        }
        return list;
    };

}
