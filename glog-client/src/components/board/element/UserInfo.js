import styled from 'styled-components'
import Images from '../../layout/Images'
import { Link } from 'react-router-dom'
import { useSelector } from 'react-redux'

function UserInfo() {
    const users = useSelector((state) => state.user)
    console.log('users :: ', users);
    return (
        <Block>
            <StyledUserInfo>
                <Link to="">
                    <Images
                        src="/mypage/thumbnail/DefaultImg.png"
                        shape="circle"
                        width={128}
                        height={128}
                    />
                    <div className="userInfo">
                        <span className="name">이름</span>
                        <span className="intro">
                            인사말
                        </span>
                    </div>
                </Link>
            </StyledUserInfo>
            <div className="line"></div>
        </Block>
    )
}
const Block = styled.div`
    max-width: 768px;
    margin: 0 auto;
    .line {
        background: rgb(233, 236, 239);
        width: 100%;
        height: 1px;
        margin-top: 2rem;
        margin-bottom: 3rem;
    }
`
const StyledUserInfo = styled.div`
    margin-top: 5.625rem;
    display: flex;
    align-items: center;
    img {
        display: inline-block;
    }
    .userInfo {
        display: inline-block;
        text-align: left;
        margin-left: 2rem;
        vertical-align: top;
        padding-top: 1rem;
        .name {
            font-size: 1.5rem;
            line-height: 1.5;
            font-weight: 600;
            margin-right: 10px;
        }
        .intro {
            display: block;
        }
    }
`

export default UserInfo
