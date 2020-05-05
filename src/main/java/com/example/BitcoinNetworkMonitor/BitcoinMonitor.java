package com.example.BitcoinNetworkMonitor;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Peer;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.core.listeners.PeerConnectedEventListener;
import org.bitcoinj.core.listeners.PeerDisconnectedEventListener;
import org.bitcoinj.net.discovery.DnsDiscovery;
import org.bitcoinj.params.MainNetParams;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Component
public class BitcoinMonitor {

    private NetworkParameters params;
    private PeerGroup peerGroup;

    public BitcoinMonitor() {
        log.info("Start NetworkMonitor");
        setupNetwork();
        peerGroup.start();
    }

    private void setupNetwork() {
        params = MainNetParams.get();
        peerGroup = new PeerGroup(params, null /* no chain */);
        peerGroup.setUserAgent("PeerMonitor", "1.0");
        peerGroup.setMaxConnections(4);
        peerGroup.addPeerDiscovery(new DnsDiscovery(params));
        peerGroup.addConnectedEventListener(new PeerConnectedEventListener() {
            @Override
            public void onPeerConnected(final Peer peer, int peerCount) {
                log.info("Hi:" + peer.getAddress().getSocketAddress().getHostString());

            }
        });
        peerGroup.addDisconnectedEventListener(new PeerDisconnectedEventListener() {
            @Override
            public void onPeerDisconnected(final Peer peer, int peerCount) {
                log.info("Bye:"+peer.getAddress().getHostname());
            }
        });

    }
}
