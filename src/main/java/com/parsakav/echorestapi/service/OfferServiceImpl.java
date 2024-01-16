package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.OfferDTO;
import com.parsakav.echorestapi.entity.BusinessOwner;
import com.parsakav.echorestapi.entity.Influencer;
import com.parsakav.echorestapi.entity.Offer;
import com.parsakav.echorestapi.repository.InfluencerRepository;
import com.parsakav.echorestapi.repository.OfferRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private InfluencerRepository repository;
    public List<OfferDTO> getAcceptedOffers(String phoneNumber){
        Influencer influencer= this.repository.getReferenceById(Long.valueOf(phoneNumber));

        List<OfferDTO> dtos = new ArrayList<>();
        Set<Offer> offers= influencer.getReceivedOffers();
        for(Offer o: offers){
            if(o.isAccept()!=null && o.isAccept()) {


                OfferDTO offerDTO = new OfferDTO();
                BeanUtils.copyProperties(o, offerDTO);
                dtos.add(offerDTO);
                offerDTO.setFullName(o.getBusinessOwner().getFullName());
                offerDTO.setInfluencerPhoneNumber(o.getInfluencer().getPhoneNumber());
                offerDTO.setBuisnessOwnerPhoneNumber(o.getBusinessOwner().getPhoneNumber());
                if (o.isAccept() != null) {
                    offerDTO.setAccept(o.isAccept());

                }
            }
        }
        return dtos;

    }

    public List<OfferDTO> getOffers(String phoneNumber){
        Influencer influencer= this.repository.getReferenceById(Long.valueOf(phoneNumber));

        List<OfferDTO> dtos = new ArrayList<>();
        Set<Offer> offers= influencer.getReceivedOffers();
        for(Offer o: offers){
            if(o.isAccept()==null) {


                OfferDTO offerDTO = new OfferDTO();
                BeanUtils.copyProperties(o, offerDTO);
                dtos.add(offerDTO);
                offerDTO.setFullName(o.getBusinessOwner().getFullName());
                offerDTO.setInfluencerPhoneNumber(o.getInfluencer().getPhoneNumber());
                offerDTO.setBuisnessOwnerPhoneNumber(o.getBusinessOwner().getPhoneNumber());
            }
        }
        return dtos;

    }

    @Override
    public boolean rejectAnOffer(String phoneNumber, int id) {
        return setAccept(phoneNumber,id,false);
    }
    private boolean setAccept(String phoneNumber,int id,boolean accept){
        Influencer influencer= repository.getReferenceById(Long.parseLong(phoneNumber));
        if(influencer==null){
            return false;
        }
        Offer o= offerRepository.getReferenceById(id);
        if(o==null){
            return false;
        }

        o.setAccept(accept);
        offerRepository.saveAndFlush(o);
        return true;
    }

    @Override
    public boolean agreeAnOffer(String phoneNumber, int id)
    {
        return setAccept(phoneNumber,id,true);
    }
    @Override
    public OfferDTO save(OfferDTO offerDTO) {
        Offer offer = new Offer();

        offer.setDate(new Date());
        offer.setText(offerDTO.getText());
        offer.setTitle(offerDTO.getTitle());
        Influencer influencer = new Influencer();
        influencer.setPhoneNumber(offerDTO.getInfluencerPhoneNumber());
        BusinessOwner businessOwner = new BusinessOwner();
        businessOwner.setPhoneNumber(offerDTO.getBuisnessOwnerPhoneNumber());
        offer.setBusinessOwner(businessOwner);
        offer.setInfluencer(influencer);
        Offer o= offerRepository.save(offer);
        OfferDTO offerDTO1 = new OfferDTO();
        BeanUtils.copyProperties(o,offerDTO1);
        return offerDTO1;

    }

    @Override
    public List<OfferDTO> getUnCommentedOffers(long bownerPhoneNumber) {
        Set<Offer> offers= offerRepository.getOfferWithoutComment(bownerPhoneNumber);
        List<OfferDTO> list = new LinkedList<>();
        for(Offer o:offers){
            OfferDTO odto= new OfferDTO();
            BeanUtils.copyProperties(o,odto);
            list.add(odto);
        }
        return list;
    }

    @Override
    public List<OfferDTO> getAllOffers(long bownerPhoneNumber) {
        Set<Offer> offers= offerRepository.getAllOffers(bownerPhoneNumber);
        List<OfferDTO> list = new LinkedList<>();
        for(Offer o:offers){
            OfferDTO odto= new OfferDTO();
            odto.setInfluencerPhoneNumber(o.getInfluencer().getPhoneNumber());
            odto.setBuisnessOwnerPhoneNumber(o.getBusinessOwner().getPhoneNumber());
            BeanUtils.copyProperties(o,odto);
            list.add(odto);
        }
        return list;
    }

    @Override
    public OfferDTO pay(int offerId) {

        Offer o= offerRepository.getReferenceById(offerId);
        if(o==null){
            return null;
        }

        o.setPaid(true);
        offerRepository.saveAndFlush(o);
        OfferDTO rv = new OfferDTO();
        BeanUtils.copyProperties(o,rv);
        return rv;
    }
}
