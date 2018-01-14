package nl.trio_opdracht.netflix_statistix.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.pages.Page;
import nl.trio_opdracht.netflix_statistix.ui.views.BottomInfoView;
import nl.trio_opdracht.netflix_statistix.ui.views.Button;
import nl.trio_opdracht.netflix_statistix.ui.views.ContainerView;
import nl.trio_opdracht.netflix_statistix.ui.views.SideMenuView;
import nl.trio_opdracht.netflix_statistix.ui.views.Window;

import static nl.trio_opdracht.netflix_statistix.Configuration.backgroundColor;

public class UserInterface implements Runnable {
    private Window window;
    private Page[] pages;

    public UserInterface(Page... pages){
        this.pages = pages;
    }

    @Override public void run() {
        window = new Window(Configuration.appName);
        window.setIcon("appicon.png");
        createComponents(window.getContentPane());
        window.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent we) {
                pages[0].closeSqlConnection();
            }
        });
        window.showWindow();
    }

    private void createComponents(Container container) {
        container.add(new BottomInfoView(Configuration.appName, Configuration.authors), BorderLayout.SOUTH);

        ContainerView contentView = new ContainerView();
        contentView.setPadding(25, 25, 25, 25);
        contentView.setBackground(backgroundColor);
        container.add(contentView);

        SideMenuView sideMenuView = new SideMenuView();
        for(Page page : pages) {
            page.setContentView(contentView);
            sideMenuView.addMenuItem(page.getTitle(), actionEvent -> {
                page.showPage();
                window.setTitle(Configuration.appName + " - " + page.getTitle());
            });
        }
        container.add(sideMenuView, BorderLayout.WEST);
        ((Button) sideMenuView.getComponents()[0]).doClick();
    }
}