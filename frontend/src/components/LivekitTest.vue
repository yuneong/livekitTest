<template>
  <div>
    <h2>LiveKit Test</h2>
    <button @click="connectRoom">방 접속</button>
    <video ref="localVideo" autoplay playsinline muted></video>
    <video ref="remoteVideo" autoplay playsinline></video>
  </div>
</template>

<script lang="ts" setup>
import { Room } from "livekit-client";
import { ref } from "vue";

const localVideo = ref<HTMLVideoElement | null>(null);
const remoteVideo = ref<HTMLVideoElement | null>(null);

const connectRoom = async () => {
  try {
    // Token API 호출

    // 랜덤 아이디로 토큰 발급
    const randomId = "user-" + Math.floor(Math.random() * 100000);

    const res = await fetch(`http://localhost:8080/api/v1/livekit/token?identity=${randomId}`);
    const data = await res.json();
    const token = data.token;

    console.log("randomId:", randomId);
    console.log("token:", token);

    const url = "ws://localhost:7880"; // LiveKit 서버 주소 (개발은 ws, 운영은 wss)
    const room = new Room();

    await room.connect(url, token);

    // 로컬 트랙 생성 & 발행
    const tracks = await room.localParticipant.createTracks();
    tracks.forEach((track) => {
      room.localParticipant.publishTrack(track);
      if (track.kind === "video" && localVideo.value) {
        track.attach(localVideo.value);
      }
    });

    // 원격 참가자 트랙 수신
    room.on("trackSubscribed", (track) => {
      if (track.kind === "video" && remoteVideo.value) {
        track.attach(remoteVideo.value);
      }
    });
  } catch (err) {
    console.error("❌ 방 접속 오류:", err);
  }
}
</script>
