package com.softwartemind;

import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    private int reqCounter = 0;
    @Autowired
    private SubscriberRepository subscriberRepository;

    @RequestMapping(value = "/subscribers", method = RequestMethod.GET)
    public String SubscribersPage(Model models) {
        reqCounter++;
        List<Subscriber> subscribersList = subscriberRepository.getSubscribers("SubscriberId");
        models.addAttribute("subscribersList", subscribersList);
        String searchValue = "";
        models.addAttribute("searchValue", searchValue);

        return "subscribers";
    }

    @RequestMapping(value = "/checkReqCounter", method = RequestMethod.GET)
    public String CheckReqCounter(Model models) {
        models.addAttribute("reqCounter", this.reqCounter);

        return "counterViewer";
    }
    @RequestMapping(value = "/filteredSubscribers", method = RequestMethod.GET)
    public String SubscribersPageSorted(Model models, @RequestParam("sortBy") String sortBy, @RequestParam("searchValue") String searchValue) {
        reqCounter++;
        List<Subscriber> subscribersList = subscriberRepository.getSubscribers(sortBy);
        models.addAttribute("subscribersList", subscribersList);
        models.addAttribute("searchValue", searchValue);

        return "subscribers";
    }

    @RequestMapping(value="/subscriberForm/{subscriberId}", method = RequestMethod.GET)
    public String addSubscriberForm(Model model, @PathVariable(name="subscriberId") int subscriberId) {
        reqCounter++;
        Subscriber subscriber = new Subscriber();
        String subsImsi = "";
        if (subscriberId != 0) {
            subscriber = subscriberRepository.getSubscriberById(subscriberId);
            subsImsi = subscriber.getImsi().toString();
        }
        model.addAttribute("subscriber",subscriber);
        model.addAttribute("subsImsi",subsImsi);

        return "subscriberForm";
    }

    @RequestMapping(value="/addSubscriber", method = RequestMethod.POST)
    public String addSubscriber(Model models, @ModelAttribute("newSubscriber") Subscriber subscriber, @ModelAttribute("subsImsi") String subsImsi) {
        reqCounter++;
        try {
            subscriber.setImsi(new BigInteger(subsImsi));
            subscriberRepository.addSubscriber(subscriber);

            return "redirect:/subscribers";
        }
        catch (Exception exception) {
            models.addAttribute("message", exception.getMessage());

            return "/errorPage";
        }

    }

    @RequestMapping(value="/updateSubscriber", method = RequestMethod.POST)
    public String updateSubscriber(Model models, @ModelAttribute("newSubscriber") Subscriber subscriber, @ModelAttribute("subsImsi") String subsImsi) {
        reqCounter++;
        try {
            subscriber.setImsi(new BigInteger(subsImsi));
            subscriberRepository.updateSubscriber(subscriber);

            return "redirect:/subscribers";
        }
        catch (Exception exception) {
            models.addAttribute("message", exception.getCause());

            return "/errorPage";
        }
    }

    @RequestMapping(value = "/deleteSubscriber/{subscriberId}", method = RequestMethod.GET)
    public String deleteSubscriber(@PathVariable(name="subscriberId") int subscriberId) {
        reqCounter++;
        subscriberRepository.deleteSubscriber(subscriberId);

        return "redirect:/subscribers";
    }
}
